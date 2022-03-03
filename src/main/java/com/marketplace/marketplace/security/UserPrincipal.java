package com.marketplace.marketplace.security;

import com.marketplace.marketplace.entity.Role;

public class UserPrincipal {
    private String username;
    private Long id;
    private Role role;

    public UserPrincipal() {
    }

    public String getUsername() {
        return username;
    }

    public UserPrincipal setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserPrincipal setId(Long id) {
        this.id = id;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public UserPrincipal setRole(Role role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        return "UserPrincipal{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPrincipal)) return false;

        UserPrincipal that = (UserPrincipal) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
