package com.marketplace.marketplace.controller;


import com.marketplace.marketplace.model.LoginModel;
import com.marketplace.marketplace.model.TokenPairModel;
import com.marketplace.marketplace.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("token")
public class TokenAuthenticationController {
    private final TokenProvider tokenProvider;


    @Autowired
    public TokenAuthenticationController(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }


    @PostMapping()
    public TokenPairModel getTokensByLoginForm(@Validated @RequestBody LoginModel loginModel){

        return tokenProvider.getTokensFromLoginModel(loginModel);
    }

    @GetMapping("/refresh")
    public TokenPairModel getTokensByRefreshToken (@RequestHeader("refresh-token") String refreshToken) {

        return tokenProvider.getTokensFromRefreshToken(refreshToken);
    }

}
