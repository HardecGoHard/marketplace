package com.marketplace.marketplace.controller;


import com.marketplace.marketplace.entity.User;
import com.marketplace.marketplace.dto.LoginDto;
import com.marketplace.marketplace.dto.RefreshTokenDto;
import com.marketplace.marketplace.dto.RegistrationModel;
import com.marketplace.marketplace.dto.TokenPairModel;
import com.marketplace.marketplace.exception.InvalidRefreshCodeException;
import com.marketplace.marketplace.security.TokenProvider;
import com.marketplace.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class TokenAuthenticationController {
    private final TokenProvider tokenProvider;
    private final UserService userService;


    @Autowired
    public TokenAuthenticationController(TokenProvider tokenProvider, UserService userService) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }


    @PostMapping("/login")
    public TokenPairModel getTokensByLoginForm(@Validated @RequestBody LoginDto loginDto){
        User user = userService.getUserByLoginModel(loginDto);
        return tokenProvider.generateTokensFromUser(user);
    }

    @GetMapping("/refresh")
    public TokenPairModel getTokensByRefreshToken (@RequestHeader("refresh-token") String refreshToken) {
        RefreshTokenDto refreshTokenDto = tokenProvider.parseRefreshToken(refreshToken);

        User user = userService.getUserByRefreshTokenModel(refreshTokenDto);
        return tokenProvider.generateTokensFromUser(user);
    }

    @PostMapping("/registration")
    public TokenPairModel registration(@Validated @RequestBody RegistrationModel registrationModel){
        User user = userService.registrateUser(registrationModel);
        return tokenProvider.generateTokensFromUser(user);
    }


    @PostMapping("/check")
    public TokenPairModel registration(@RequestHeader("access-token") String accessToken){
        if (tokenProvider.validateToken(accessToken))
            return new TokenPairModel().setAccessToken(accessToken);
        else
            throw new InvalidRefreshCodeException("Invalid token");
    }




}
