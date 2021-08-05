package br.com.zup.ecommerce.config.security.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @JsonProperty
    @NotBlank
    private String email;
    @JsonProperty
    @NotBlank
    private String senha;

    public LoginRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken CredentialsAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(this.email, this.senha);
    }
}
