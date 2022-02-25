package com.marketplace.marketplace.security;

import com.marketplace.marketplace.model.TokenPairModel;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        TokenPairModel tokens = (TokenPairModel) authentication.getCredentials();
        Collection<SimpleGrantedAuthority> authorities = new LinkedList<>();
        if (tokens.getAccessToken() != null && tokens.getAccessToken().startsWith("mock")){
            authorities.add(new SimpleGrantedAuthority("ROLE_user"));
            return new UsernamePasswordAuthenticationToken("TokenPair", tokens, authorities);
        }
        if(tokens.getRefreshToken() != null && tokens.getRefreshToken().startsWith("mock")){
            authorities.add(new SimpleGrantedAuthority("ROLE_user_refresh"));
            return new UsernamePasswordAuthenticationToken("TokenPair", tokens, authorities);
        }
        throw new BadCredentialsException("bad mock");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
