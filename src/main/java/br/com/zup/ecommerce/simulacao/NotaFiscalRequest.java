package br.com.zup.ecommerce.simulacao;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class NotaFiscalRequest {
    @JsonProperty @NotNull
    private Long idComprador;

    @JsonProperty @NotNull
    private Long idCompra;

    public NotaFiscalRequest(Long idComprador, Long idCompra) {
        this.idComprador = idComprador;
        this.idCompra = idCompra;
    }

    @Override
    public String toString() {
        return "NotaFiscalRequest{" +
                "idComprador=" + idComprador +
                ", idCompra=" + idCompra +
                '}';
    }
}
