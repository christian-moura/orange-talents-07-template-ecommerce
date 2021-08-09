package br.com.zup.ecommerce.produto.opiniao;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OpiniaoProdutoResponse {

    @JsonProperty
    private Integer quantidade;
    @JsonProperty
    private Double media;
    @JsonProperty
    private List<OpiniaoResponse> opinioes;

    public OpiniaoProdutoResponse(@NotNull Set<Opiniao> opinioes) {
        this.quantidade = opinioes.size();
        this.media = mediaOpinioes(opinioes);
        this.opinioes = opinioes.stream().map(OpiniaoResponse::new).collect(Collectors.toList());
    }

    private Double mediaOpinioes(@NotNull Set<Opiniao> opinioes) {
        List<Integer> notas = opinioes.stream().map(Opiniao::getNota).collect(Collectors.toList());
        return notas.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
    }
}
