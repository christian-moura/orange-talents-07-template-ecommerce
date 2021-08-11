package br.com.zup.ecommerce.compra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public enum GatewayPagamento {
    PAGSEGURO("pagseguro"),
    PAYPAL("paypal");

    private String gateway;

    GatewayPagamento(@NotBlank String gateway) {
        this.gateway = gateway;
    }

    public String getGateway() {
        return gateway;
    }

    public String getGatewayUri(@NotNull Long idCompra) {
        if(this.gateway.equals("PAGSEGURO") || this.gateway.equals("pagseguro") ){
            return "pagseguro.com?returnId="+idCompra+"&redirectUrl=http://localhost:8080/api/produto/"+
                    idCompra+"/pagseguro-retorno-compra".toString();
        }
        return "paypal.com?buyerId="+idCompra+"&redirectUrl=http://localhost:8080/api/produto/"+
                idCompra+"/paypal-retorno-compra";
    }
}
