package br.com.zup.ecommerce.produto;

import br.com.zup.ecommerce.categoria.Categoria;
import br.com.zup.ecommerce.produto.caracteristica.CaracteristicaRequest;
import br.com.zup.ecommerce.usuario.Usuario;
import br.com.zup.ecommerce.validations.ExistValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoRequest {

    @JsonProperty @NotBlank
    private String nome;
    @JsonProperty @Min(1)
    private BigDecimal valor;
    @JsonProperty  @Min(0) @NotNull
    private Integer quantidade;
    @JsonProperty  @Length(max = 1000)
    @NotBlank
    private String descricao;
    @ExistValue(entity = Categoria.class, attribute = "id", message = "Categoria inexistente.")
    @JsonProperty @NotNull
    private Long categoria;
    @JsonProperty @NotNull @Valid @Size(min = 3) @UniqueElements(message = "Não é possível inserir características iguais.")
    private List<CaracteristicaRequest> caracteristicas;

    public Produto toProduto(EntityManager entityManager, Usuario usuario){
        Categoria categoria = entityManager.find(Categoria.class,this.categoria);
        return new Produto(this.nome,this.valor, this.quantidade,this.descricao,
                categoria, caracteristicas.stream().map(CaracteristicaRequest::toCaracteristica).collect(Collectors.toSet()), usuario);
    }
}
