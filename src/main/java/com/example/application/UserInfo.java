package com.example.application;

import dev.hilla.Nonnull;

import java.util.Collection;
import java.util.Collections;

public class UserInfo {

    @Nonnull
    private String name;
    @Nonnull
    private Collection<String> authorities;

    public UserInfo(String name, Collection<String> authorities) {
        this.name = name;
        this.authorities = Collections.unmodifiableCollection(authorities);
    }

    public String getName() {
        return name;
    }

    public Collection<String> getAuthorities() {
        return authorities;
    }

}