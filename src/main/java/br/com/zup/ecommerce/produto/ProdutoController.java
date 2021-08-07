package br.com.zup.ecommerce.produto;

import br.com.zup.ecommerce.config.handler.exception.PersonalizadaException;
import br.com.zup.ecommerce.produto.imagem.ImagemProdutoRequest;
import br.com.zup.ecommerce.produto.imagem.UploaderFake;
import br.com.zup.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private EntityManager entityManager;
    private UploaderFake uploaderFake;

    @Autowired
    public ProdutoController(EntityManager entityManager, UploaderFake uploaderFake) {
        this.entityManager = entityManager;
        this.uploaderFake = uploaderFake;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastroProduto(@Valid @RequestBody ProdutoRequest produtoRequest,
                                             @AuthenticationPrincipal Usuario usuario){
        entityManager.persist(produtoRequest.toProduto(entityManager, usuario));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity<?> inserirImagemProduto(@Valid ImagemProdutoRequest imagemProdutoRequest,
                                                  @PathVariable Long id,
                                                  @AuthenticationPrincipal Usuario usuario){
        Produto produto = entityManager.find(Produto.class,id);
        if(produto==null) throw new PersonalizadaException(HttpStatus.NOT_FOUND,"id","Produto inexistente.");
        if(!produto.getUsuario().getId().equals(usuario.getId()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado. Usuário sem permissão para o recurso.");
        produto.inserirImagens(uploaderFake.enviarFotos(imagemProdutoRequest.getImagens()));
        entityManager.merge(produto);
        return ResponseEntity.ok().build();
    }
}
