package br.com.zup.ecommerce.compra;

import br.com.zup.ecommerce.produto.Produto;
import br.com.zup.ecommerce.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

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
    private StatusPagamento statusPagamento;

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
        this.statusPagamento = StatusPagamento.INICIADA;
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

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }
}
