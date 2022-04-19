package com.marketplace.marketplace.security;


import com.marketplace.marketplace.entity.Role;
import com.marketplace.marketplace.entity.User;
import com.marketplace.marketplace.model.RefreshTokenModel;
import com.marketplace.marketplace.model.TokenPairModel;
import com.marketplace.marketplace.service.UserService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/* TODO
 *   - Перевести токены с юзернейма на айди в базе(подумать о логике при разделении на
 *     микросервис аутентификации)
 */
@Component
public class TokenProvider {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final long accessTokenValidityInMilliseconds;

    private final UserService userService;

    @Autowired
    public TokenProvider(@Value("${marketplace.security.accessTokenValidityInSeconds}") Long accessTokenValidityInSeconds,
                         UserService userService) {
        this.userService = userService;
        this.accessTokenValidityInMilliseconds = accessTokenValidityInSeconds * 1000;
    }

    public TokenPairModel getTokensFromRefreshToken(String refreshToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(refreshToken)
                .getBody();
        User user = userService.getByUsername(claims.getSubject());
        if (claims.get("token-type").equals("refresh") &&
                claims.get("refresh-id").equals(user.getRefreshCode())) {

            return generateTokensFromUser(user);
            //} else {
            // TODO something like throw exception
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
                .claim("role", user.getRole().name())
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

    public TokenPairModel generateTokensFromUser(User user) {
        TokenPairModel result = new TokenPairModel();
        result.setAccessToken(generateAccessToken(user));
        result.setRefreshToken(generateRefreshToken(user));
        return result;
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

    public UserPrincipal parseAccessToken(String accessToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(accessToken)
                .getBody();
        return new UserPrincipal()
                .setUsername(claims.getSubject())
                .setId(claims.get("user-id", Long.class))
                .setRole(Role.valueOf((String) claims.get("role")));
    }

    public RefreshTokenModel parseRefreshToken(String refreshToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(refreshToken)
                .getBody();
        return new RefreshTokenModel()
                .setId(claims.get("user-id", Long.class))
                .setUsername(claims.getSubject())
                .setRefreshCode(claims.get("refresh-id", String.class));
    }
}
