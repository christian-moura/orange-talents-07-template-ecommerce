package br.com.zup.ecommerce.compra;

import br.com.zup.ecommerce.config.handler.exception.PersonalizadaFieldsException;
import br.com.zup.ecommerce.email.Email;
import br.com.zup.ecommerce.produto.Produto;
import br.com.zup.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/")
public class CompraController {

    private EntityManager entityManager;
    private Email email;

    @Autowired
    public CompraController(EntityManager entityManager, Email email) {
        this.entityManager = entityManager;
        this.email = email;
    }

    @PostMapping("produto/{id}/compra")
    @Transactional
    public ResponseEntity<?> comprarProduto(@Valid @RequestBody CompraRequest compraRequest ,
                                            @PathVariable Long id,
                                            @AuthenticationPrincipal Usuario usuario){
        Produto produto = entityManager.find(Produto.class,id);
        if(produto==null) throw new PersonalizadaFieldsException(HttpStatus.NOT_FOUND,"id","Produto inexistente.");
        Compra compra = compraRequest.toCompra(produto,usuario);
        entityManager.persist(compra);
        email.novaCompra(compra);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(compra.getGatewayPagamento().getGatewayUri(compra.getId())))
                .build();
    }

}
