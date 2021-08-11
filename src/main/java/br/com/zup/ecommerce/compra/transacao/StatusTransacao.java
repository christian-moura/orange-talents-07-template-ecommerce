package br.com.zup.ecommerce.compra.transacao;

import javax.validation.constraints.NotBlank;

public enum StatusTransacao {
        SUCESSO("sucesso"),
        ERRO("erro");

    private String status;

    StatusTransacao(@NotBlank String status) {
        this.status = status;
    }
}
