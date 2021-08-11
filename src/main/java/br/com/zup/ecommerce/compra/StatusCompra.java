package br.com.zup.ecommerce.compra;

import javax.validation.constraints.NotBlank;

public enum StatusCompra {
    INICIADA("iniciada"),
    AGUARDANDO_PAGAMENTO("aguardando_pagamento"),
    FINALIZADA("finalizada");
    private String status;

    StatusCompra(@NotBlank String status) {
        this.status = status;
    }
}
