package com.sport.model.impl;

import java.util.Objects;

public class Team {
    private String name;

    public Team(String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validateName(String name) {
        Objects.requireNonNull(name,"Team name cannot be null");
        if (name.isBlank()) {
            throw new IllegalArgumentException("Team name cannot be blank");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team team)) return false;
        return Objects.equals(getName(), team.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
