package com.example.demo.requests;

import com.example.demo.repositories.UserRepository;
import com.example.demo.validations.annotations.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class StoreUserRequest extends UpdateUserRequest {
    @NotNull
    @NotEmpty
    @UniqueConstraint(method = "findByUsername", repository = UserRepository.class)
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
