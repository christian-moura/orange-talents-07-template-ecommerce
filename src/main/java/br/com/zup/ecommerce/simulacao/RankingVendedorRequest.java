package br.com.zup.ecommerce.simulacao;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class RankingVendedorRequest {

    @JsonProperty @NotNull
    private Long idVendedor;

    @JsonProperty @NotNull
    private Long idCompra;

    public RankingVendedorRequest(Long idVendedor, Long idCompra) {
        this.idVendedor = idVendedor;
        this.idCompra = idCompra;
    }

    @Override
    public String toString() {
        return "RankingVendedorRequest{" +
                "idVendedor=" + idVendedor +
                ", idCompra=" + idCompra +
                '}';
    }
}
