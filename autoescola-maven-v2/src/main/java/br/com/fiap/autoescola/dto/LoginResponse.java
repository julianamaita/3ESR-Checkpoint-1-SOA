package br.com.fiap.autoescola.dto;

public record LoginResponse(String token, String tokenType) {
    public static LoginResponse bearer(String token) { return new LoginResponse(token, "Bearer"); }
}
