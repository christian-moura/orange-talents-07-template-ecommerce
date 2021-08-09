package br.com.zup.ecommerce.produto.caracteristica;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class CaracteristicaResponse {

    @JsonProperty
    private String nome;
    @JsonProperty
    private String descricao;

    public CaracteristicaResponse(@NotNull Caracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }
}
