package br.com.zup.ecommerce.compra;

import javax.validation.constraints.NotBlank;

public enum StatusPagamento {
    INICIADA("iniciada");

    private String status;

    StatusPagamento(@NotBlank String status) {
        this.status = status;
    }
}
