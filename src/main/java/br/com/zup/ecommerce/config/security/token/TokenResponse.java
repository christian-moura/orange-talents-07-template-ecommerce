package br.com.zup.ecommerce.config.security.token;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse {

    @JsonProperty
    private String token;
    @JsonProperty
    private String tipo;

    public TokenResponse(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }
}
