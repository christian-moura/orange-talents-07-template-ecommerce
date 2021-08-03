package br.com.zup.ecommerce.categoria;

import br.com.zup.ecommerce.validations.ExistValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class CategoriaMaeRequest {

    @JsonProperty("idCategoria")
    @NotNull
    @ExistValue(entity = Categoria.class, attribute = "id", message = "Categoria mãe informada é inexistente.")
    private Long idCategoria;

    public Long getIdCategoria() {
        return idCategoria;
    }
}
