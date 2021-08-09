package br.com.zup.ecommerce.produto.pergunta;

import br.com.zup.ecommerce.produto.Produto;
import br.com.zup.ecommerce.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class PerguntaRequest {

    @JsonProperty @NotBlank
    private String titulo;

    public Pergunta toPergunta (Usuario usuario, Produto produto){
        return new Pergunta(this.titulo, usuario, produto);
    }
}
