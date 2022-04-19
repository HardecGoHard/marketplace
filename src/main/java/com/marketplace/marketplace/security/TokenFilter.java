package com.marketplace.marketplace.security;

import com.marketplace.marketplace.model.TokenPairModel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedList;

public class TokenFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String accessToken = request.getHeader("access-token");
        String refreshToken = request.getHeader("refresh-token");

        if(accessToken != null || refreshToken != null) {
            TokenPairModel tokenPairModel = new TokenPairModel(accessToken, refreshToken);
            Authentication authentication = new UsernamePasswordAuthenticationToken("TokenPair", tokenPairModel);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
