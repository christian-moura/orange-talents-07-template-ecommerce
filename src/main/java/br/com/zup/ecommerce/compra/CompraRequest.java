package br.com.zup.ecommerce.compra;

import br.com.zup.ecommerce.produto.Produto;
import br.com.zup.ecommerce.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraRequest {

    @JsonProperty
    @Positive @NotNull
    private Integer quantidade;

    @JsonProperty
    @Enumerated @NotNull
    private GatewayPagamento gatewayPagamento;

    public Compra toCompra(Produto produto, Usuario usuario){
        return new Compra(this.quantidade,produto.getValor(),usuario,produto, this.gatewayPagamento);
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
