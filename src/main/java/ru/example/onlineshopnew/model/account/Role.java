package ru.example.onlineshopnew.model.account;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
