package com.marketplace.marketplace.util;

import com.marketplace.marketplace.entity.Role;
import com.marketplace.marketplace.entity.Status;
import com.marketplace.marketplace.entity.User;
import com.marketplace.marketplace.security.JwtAuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class JwtAuthUserBuilder {

    public static JwtAuthUser getJwtAuthUser(User user) {
        return new JwtAuthUser(
                user.getUsername(),
                user.getUsername(),
                user.getPassword(),
                isActive(user.getStatus()),
                user.getUpdatedAt(),
                getAuthAuthorityCollection(user.getRoles())
    );
    }

    private static boolean isActive(Status status){
        return status.name().equals("ACTIVE");
    }

    private static Collection<? extends GrantedAuthority> getAuthAuthorityCollection(Collection<Role> userRoles){
        return userRoles.stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getRole()))
                .collect(Collectors.toSet());
    }
}
