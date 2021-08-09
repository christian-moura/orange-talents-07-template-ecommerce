package br.com.zup.ecommerce.produto.pergunta;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class PerguntaResponse {

    @JsonProperty
    private String titulo;

    public PerguntaResponse(@NotNull Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
    }
}
