package br.com.zup.ecommerce.produto.opiniao;

import br.com.zup.ecommerce.produto.Produto;
import br.com.zup.ecommerce.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;

public class OpiniaoRequest {

    @JsonProperty @NotNull
    @Min(1) @Max(5)
    private Integer nota;

    @JsonProperty @NotBlank
    private String titulo;

    @JsonProperty @NotBlank
    @Size(max = 500)
    private String descricao;

    public Opiniao toOpiniao(Usuario usuario, Produto produto){
        return new Opiniao(this.nota,this.titulo, this.descricao,usuario,produto);
    }
}
