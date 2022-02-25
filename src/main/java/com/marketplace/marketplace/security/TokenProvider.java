package com.marketplace.marketplace.security;


import com.marketplace.marketplace.model.LoginModel;
import com.marketplace.marketplace.model.TokenPairModel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenProvider {
    public TokenPairModel getTokensFromLoginModel(LoginModel loginModel){
        TokenPairModel result = new TokenPairModel();
        result.setAccessToken("mock"+loginModel.getUsername());
        result.setRefreshToken("mockRefresh"+loginModel.getUsername());
        return result;
    }

    public TokenPairModel getTokensFromRefreshToken(String refreshToken){
        TokenPairModel result = new TokenPairModel();
        result.setAccessToken("mock");
        result.setRefreshToken("mockRefresh "+refreshToken);
        return result;
    }
}
