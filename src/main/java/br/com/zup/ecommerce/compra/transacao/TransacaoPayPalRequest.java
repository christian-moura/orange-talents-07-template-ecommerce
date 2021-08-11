package br.com.zup.ecommerce.compra.transacao;

import br.com.zup.ecommerce.compra.Compra;
import br.com.zup.ecommerce.validations.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TransacaoPayPalRequest {

    @JsonProperty("compra")
    @NotNull
    private Integer idCompra;

    @JsonProperty
    @NotBlank @UniqueValue(entity = Transacao.class, attribute = "idTransacaoGateway", message = "Transação negada, já existe uma transação com o respectivo id")
    private String idTransacao;

    @JsonProperty
    @NotNull
    @Min(0)@Max(1)
    private Integer status;

    public Transacao toTransacao(Compra compra){
        if(this.status.equals(0))  return new Transacao(StatusTransacao.ERRO,this.idTransacao, compra);
        return new Transacao(StatusTransacao.SUCESSO,this.idTransacao, compra);
    }
}
