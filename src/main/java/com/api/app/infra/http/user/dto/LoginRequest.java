package com.api.app.infra.http.user.dto;

public record LoginRequest(String email,
    String password) {

}
