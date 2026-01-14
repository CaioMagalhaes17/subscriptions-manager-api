package com.api.app.infra.http.user.dto;

public record RegisterRequest(String email,
        String name,
        String password) {

}
