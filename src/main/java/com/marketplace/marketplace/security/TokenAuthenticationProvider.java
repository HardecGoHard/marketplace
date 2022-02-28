package com.marketplace.marketplace.security;

import com.marketplace.marketplace.model.TokenPairModel;
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
            authorities.add(new SimpleGrantedAuthority("ROLE_" + tokenProvider.getRoleFromAccessToken(tokens.getAccessToken())));
            return new UsernamePasswordAuthenticationToken("TokenPair", tokens, authorities);
        }
        if (tokens.getRefreshToken() != null && tokenProvider.validateToken(tokens.getRefreshToken())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER_REFRESH"));
            return new UsernamePasswordAuthenticationToken("TokenPair", tokens, authorities);
        }
        throw new BadCredentialsException("bad mock");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
