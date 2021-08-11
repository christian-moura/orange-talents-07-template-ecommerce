package br.com.zup.ecommerce.compra.transacao;

import br.com.zup.ecommerce.compra.Compra;
import br.com.zup.ecommerce.validations.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TransacaoPagSeguroRequest {

    @JsonProperty("compra")
    @NotNull
    private Integer idCompra;

    @JsonProperty
    @NotBlank @UniqueValue(entity = Transacao.class, attribute = "idTransacaoGateway", message = "Transação negada, já existe uma transação com o respectivo id")
    private String idTransacao;

    @JsonProperty @NotBlank
    private String status;

    public Transacao toTransacao(Compra compra){
        if(this.status.equals("ERRO")) return new Transacao(StatusTransacao.ERRO,this.idTransacao, compra);
        return new Transacao(StatusTransacao.SUCESSO,this.idTransacao, compra);
    }
}
