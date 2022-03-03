package com.marketplace.marketplace.controller;


import com.marketplace.marketplace.entity.User;
import com.marketplace.marketplace.model.LoginModel;
import com.marketplace.marketplace.model.RefreshTokenModel;
import com.marketplace.marketplace.model.TokenPairModel;
import com.marketplace.marketplace.security.TokenProvider;
import com.marketplace.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("token")
public class TokenAuthenticationController {
    private final TokenProvider tokenProvider;
    private final UserService userService;


    @Autowired
    public TokenAuthenticationController(TokenProvider tokenProvider, UserService userService) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }


    @PostMapping()
    public TokenPairModel getTokensByLoginForm(@Validated @RequestBody LoginModel loginModel){
        User user = userService.getUserByLoginModel(loginModel);
        return tokenProvider.generateTokensFromUser(user);
    }

    @GetMapping("/refresh")
    public TokenPairModel getTokensByRefreshToken (@RequestHeader("refresh-token") String refreshToken) {
        RefreshTokenModel refreshTokenModel = tokenProvider.parseRefreshToken(refreshToken);

        User user = userService.getUserByRefreshTokenModel(refreshTokenModel);
        return tokenProvider.generateTokensFromUser(user);
    }

}
