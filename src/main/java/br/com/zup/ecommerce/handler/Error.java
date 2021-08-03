package br.com.zup.ecommerce.handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.FieldError;

public class Error {
    @JsonProperty
    private String campo;
    @JsonProperty
    private String mensagem;

    public Error(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }
    public Error(FieldError error) {
        this.campo = error.getField();
        this.mensagem = error.getDefaultMessage();
    }
}
