package br.com.zup.ecommerce.config.handler;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorSingleMessage {

    @JsonProperty
    private String mensagem;

    public ErrorSingleMessage(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
