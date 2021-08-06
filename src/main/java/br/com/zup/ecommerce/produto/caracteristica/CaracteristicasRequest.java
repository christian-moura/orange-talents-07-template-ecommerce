package br.com.zup.ecommerce.produto.caracteristica;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CaracteristicasRequest {

   @JsonProperty @NotBlank
    private String nome;

   @JsonProperty @NotBlank
    private String descricao;

   public Caracteristica toCaracteristica(){
       return new Caracteristica(this.nome, this.descricao);
   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicasRequest that = (CaracteristicasRequest) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
