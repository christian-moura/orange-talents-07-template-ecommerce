package br.com.zup.ecommerce.compra.transacao;

import br.com.zup.ecommerce.compra.Compra;
import br.com.zup.ecommerce.config.handler.exception.PersonalizadaFieldsException;
import br.com.zup.ecommerce.validations.ExistValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class TransacaoController {

    private ProcessaTransacao processaTransacao;
    private EntityManager entityManager;

    @Autowired
    public TransacaoController(EntityManager entityManager, ProcessaTransacao processaTransacao) {
        this.entityManager = entityManager;
        this.processaTransacao =processaTransacao;

    }

    @Transactional
    @PostMapping("/produto/{id}/pagseguro-retorno-compra")
    public ResponseEntity<?> responsePagSeguro(@PathVariable @ExistValue(entity = Compra.class, attribute = "id") Long id,
                                               @Valid @RequestBody TransacaoPagSeguroRequest transacaoPagSeguroRequest,
                                               @RequestHeader (name="Authorization") String token ) {
        Compra compra = entityManager.find(Compra.class, id);
        if(compra==null) throw new PersonalizadaFieldsException(HttpStatus.NOT_FOUND,"id","Compra inexistente.");
        Transacao transacao = transacaoPagSeguroRequest.toTransacao(compra);
        processaTransacao.processamento(entityManager, compra, transacao,token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/produto/{id}/paypal-retorno-compra")
    @Transactional
    public ResponseEntity<?> responsePaypal(@PathVariable  Long id,
                                            @Valid @RequestBody TransacaoPayPalRequest payPalRequest,
                                            @RequestHeader("Authorization") String token) {
        Compra compra = entityManager.find(Compra.class, id);
        if(compra==null) throw new PersonalizadaFieldsException(HttpStatus.NOT_FOUND,"id","Compra inexistente.");
        Transacao transacao = payPalRequest.toTransacao(compra);
        processaTransacao.processamento(entityManager, compra, transacao, token);
        return ResponseEntity.ok().build();
    }
}
