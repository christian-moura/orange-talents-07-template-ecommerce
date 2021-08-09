package br.com.zup.ecommerce.produto;

import br.com.zup.ecommerce.produto.caracteristica.CaracteristicaResponse;
import br.com.zup.ecommerce.produto.imagem.ImagemProduto;
import br.com.zup.ecommerce.produto.opiniao.OpiniaoProdutoResponse;
import br.com.zup.ecommerce.produto.pergunta.PerguntaResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoResponse {

    @JsonProperty
    private String nome;
    @JsonProperty
    private BigDecimal valor;
    @JsonProperty
    private String descricao;
    @JsonProperty
    private Set<CaracteristicaResponse> caracteristicas;
    @JsonProperty
    private OpiniaoProdutoResponse opinioesProduto;
    @JsonProperty
    private List<PerguntaResponse> perguntas;
    @JsonProperty
    private List<String> imagens;

    public ProdutoResponse(@NotNull Produto produto) {
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.descricao = produto.getDescricao();
        this.caracteristicas = produto.getCaracteristicas().stream().map(CaracteristicaResponse::new).collect(Collectors.toSet());
        this.opinioesProduto = new OpiniaoProdutoResponse(produto.getOpinioes());
        this.perguntas = produto.getPerguntas().stream().map(PerguntaResponse::new).collect(Collectors.toList());
        this.imagens = produto.getImagens().stream().map(ImagemProduto::getUrl).collect(Collectors.toList());
    }
}
