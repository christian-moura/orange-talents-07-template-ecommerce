package br.com.zup.ecommerce.produto.opiniao;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class OpiniaoResponse {

    @JsonProperty
    private String titulo;

    @JsonProperty
    private Integer nota;

    @JsonProperty
    private String descricao;

    public OpiniaoResponse(@NotNull Opiniao opiniao) {
        this.titulo = opiniao.getTitulo();
        this.nota = opiniao.getNota();
        this.descricao = opiniao.getDescricao();
    }
}
