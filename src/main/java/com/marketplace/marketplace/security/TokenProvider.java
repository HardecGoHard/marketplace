package com.marketplace.marketplace.security;


import com.marketplace.marketplace.entity.User;
import com.marketplace.marketplace.model.LoginModel;
import com.marketplace.marketplace.model.TokenPairModel;
import com.marketplace.marketplace.service.UserService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/* TODO
 *   - Перенести часть логики в userAuthenticationService
 *   - Вынести повторяющийся код в метод generateTokensFromUser
 *   - Перевести токены с юзернейма на айди в базе(подумать о логике при разделении на
 *     микросервис аутентификации)
 *   - Добавить логику изменения рефреш айди после каждого использования рефреш токена
 *   - Подумать о том, в каких местах должен валидироваться токен, по идее это происходит
 *     в аусПровайдере, и в getTokensFromRefreshToken в провайдере это не нужно.
 *   - Вынести логику валидации рефреш токена в отдельный метод и вызывать его в аусПровайдере
 *     пока что аусПровайдер просто смотрит сигнатуру, затем приходит к выполнению метода тут
 *     и если рефрш айди не совпадает то он просто ничего не выдает.
 */
@Component
public class TokenProvider {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final long accessTokenValidityInMilliseconds;

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public TokenProvider(@Value("${marketplace.security.accessTokenValidityInSeconds}") Long accessTokenValidityInSeconds,
                         UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.accessTokenValidityInMilliseconds = accessTokenValidityInSeconds * 1000;
    }

    public TokenPairModel getTokensFromLoginModel(LoginModel loginModel) {
        User user = userService.getByUsername(loginModel.getUsername());

        if (passwordEncoder.matches(loginModel.getPassword(), user.getPassword())) {

            TokenPairModel result = new TokenPairModel();
            result.setAccessToken(generateAccessToken(user));
            result.setRefreshToken(generateRefreshToken(user));

            return result;
            //} else {
            // TODO something like throw exception
        }
        return null;
    }

    public TokenPairModel getTokensFromRefreshToken(String refreshToken) {

        if (validateToken(refreshToken)) {
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(refreshToken)
                    .getBody();
            User user = userService.getByUsername(claims.getSubject());
            if (claims.get("token-type").equals("refresh") &&
                    claims.get("refresh-id").equals(user.getRefreshCode())) {

                TokenPairModel result = new TokenPairModel();
                result.setAccessToken(generateAccessToken(user));
                result.setRefreshToken(generateRefreshToken(user));

                return result;
                //} else {
                // TODO something like throw exception
            }

        }
        return null;
    }

    private String generateAccessToken(User user) {

        long now = (new Date()).getTime();
        Date validity = new Date(now + accessTokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("user-id", user.getId())
                .claim("token-type", "access")
                .claim("role", user.getRole())
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();

    }

    private String generateRefreshToken(User user) {

        long now = (new Date()).getTime();
        Date validity = new Date(now + accessTokenValidityInMilliseconds * 100);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("user-id", user.getId())
                .claim("token-type", "refresh")
                .claim("refresh-id", user.getRefreshCode())
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();

    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            System.out.println("Invalid JWT signature.");
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT token compact of handler are invalid.");
        }
        return false;
    }

    public String getRoleFromAccessToken(String accessToken) {
        return (String) Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(accessToken)
                .getBody()
                .get("role");
    }
}
