package br.com.zup.ecommerce.categoria;

import br.com.zup.ecommerce.validations.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @JsonProperty
    @NotBlank
    @UniqueValue(entity = Categoria.class, attribute = "nome", message = "Nome indisponível, selecione outro.")
    private String nome;
    @JsonProperty
    @Valid
    private CategoriaMaeRequest categoriaMae;

    public Categoria toCategoria(CategoriaRepository categoriaRepository){
        if(categoriaMae==null) return new Categoria(this.nome);
        return new Categoria(this.nome, categoriaRepository.findById(categoriaMae.getIdCategoria()).get());
    }

    public CategoriaMaeRequest getCategoriaMae() {
        return categoriaMae;
    }
}
