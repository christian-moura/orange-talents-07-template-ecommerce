package br.com.zup.ecommerce.compra;

import br.com.zup.ecommerce.compra.transacao.Transacao;
import br.com.zup.ecommerce.config.handler.exception.PersonalizadaSingleMessageException;
import br.com.zup.ecommerce.produto.Produto;
import br.com.zup.ecommerce.usuario.Usuario;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @Column(nullable = false)
    private Integer quantidade;

    @Positive
    @Column(nullable = false)
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    private Produto produto;

    @Enumerated
    @NotNull
    private GatewayPagamento gatewayPagamento;

    @Enumerated
    @NotNull
    private StatusCompra statusCompra;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE, fetch= FetchType.EAGER)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {
    }

    public Compra(@NotNull @Positive Integer quantidade, @NotNull @Positive BigDecimal valor,
                  @NotNull Usuario usuario, @NotNull Produto produto,
                  @NotNull GatewayPagamento gatewayPagamento) {
        this.quantidade = quantidade;
        this.valor = valor;
        this.usuario = usuario;
        this.produto = produto;
        this.gatewayPagamento = gatewayPagamento;
        this.statusCompra = StatusCompra.INICIADA;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public StatusCompra getStatusCompra() {
        return statusCompra;
    }

    public void processaTransacao(Transacao transacao){
        if(this.statusCompra.equals(StatusCompra.FINALIZADA)) throw new PersonalizadaSingleMessageException(HttpStatus.BAD_REQUEST,"Processamento bloqueado. Compra já está finalizada com pagamento efetuado");
        this.transacoes.add(transacao);
        if(transacao.tracacaoSucedida()) this.statusCompra = StatusCompra.FINALIZADA;
        else this.statusCompra =StatusCompra.AGUARDANDO_PAGAMENTO;
    }
}
