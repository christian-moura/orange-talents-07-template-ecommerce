package br.com.zup.ecommerce.compra;

import br.com.zup.ecommerce.config.handler.exception.PersonalizadaFieldsException;
import br.com.zup.ecommerce.produto.Produto;
import br.com.zup.ecommerce.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

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
        if(!produto.abateEstoque(this.quantidade)) throw new PersonalizadaFieldsException(HttpStatus.NOT_FOUND,"quantidade","Estoque indisponível.");
        return new Compra(this.quantidade,produto.getValor(),usuario,produto, this.gatewayPagamento);
    }
}
