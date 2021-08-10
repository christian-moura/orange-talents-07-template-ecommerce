package br.com.zup.ecommerce.compra;

import javax.validation.constraints.NotBlank;

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
}
