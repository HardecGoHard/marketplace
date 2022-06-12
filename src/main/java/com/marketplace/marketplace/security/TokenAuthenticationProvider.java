package com.marketplace.marketplace.security;

import com.marketplace.marketplace.dto.TokenPairModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;

//TODO подумать что лучше держать в принципал
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final TokenProvider tokenProvider;

    @Autowired
    public TokenAuthenticationProvider(@Lazy TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        TokenPairModel tokens = (TokenPairModel) authentication.getCredentials();
        Collection<SimpleGrantedAuthority> authorities = new LinkedList<>();

        if (tokens.getAccessToken() != null && tokenProvider.validateToken(tokens.getAccessToken())) {
            UserPrincipal userPrincipal = tokenProvider.parseAccessToken(tokens.getAccessToken());
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userPrincipal.getRole()));
            return new UsernamePasswordAuthenticationToken(userPrincipal, tokens.getAccessToken(), authorities);
        } else if (tokens.getRefreshToken() != null && tokenProvider.validateToken(tokens.getRefreshToken())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER_REFRESH"));//special role for refreshToken
                                                                                 //refreshId would be checked later
                                                                                 //in /refresh logic
            return new UsernamePasswordAuthenticationToken("REFRESH_TOKEN", tokens.getRefreshToken(), authorities);
        }
        throw new BadCredentialsException("token not valid");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
