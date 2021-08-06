package br.com.zup.ecommerce.produto;

import br.com.zup.ecommerce.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private EntityManager entityManager;

    public ProdutoController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastroProduto(@Valid @RequestBody ProdutoRequest produtoRequest,
                                             @AuthenticationPrincipal Usuario usuario){
        entityManager.persist(produtoRequest.toProduto(entityManager, usuario));
        return ResponseEntity.ok().build();
    }
}
