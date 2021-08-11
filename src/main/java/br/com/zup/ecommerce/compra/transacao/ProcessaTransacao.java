package br.com.zup.ecommerce.compra.transacao;

import br.com.zup.ecommerce.compra.Compra;
import br.com.zup.ecommerce.compra.StatusCompra;
import br.com.zup.ecommerce.config.handler.exception.PersonalizadaSingleMessageException;
import br.com.zup.ecommerce.email.Email;
import br.com.zup.ecommerce.simulacao.RecursosExternos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class ProcessaTransacao {

    private Email email;
    private RecursosExternos recursosExternos;

    @Autowired
    public ProcessaTransacao(Email email, RecursosExternos recursosExternos) {
        this.email = email;
        this.recursosExternos = recursosExternos;
    }

    public void processamento(EntityManager entityManager, Compra compra, Transacao transacao, String token){
        compra.processaTransacao(transacao);
        entityManager.merge(compra);
        if(compra.getStatusCompra().equals(StatusCompra.FINALIZADA)){
            email.compraFinalizada(compra);
            recursosExternos.gerarNotaFiscal(compra, token);
            recursosExternos.atualizarRankingVendedores(compra, token);
        }
        else{
            email.falhaNoPagamento(compra);
            throw new PersonalizadaSingleMessageException(HttpStatus.BAD_REQUEST,compra.getGatewayPagamento().getGatewayUri(compra.getId()));
        }
    }
}
