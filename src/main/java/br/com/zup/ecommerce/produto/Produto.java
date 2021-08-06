package br.com.zup.ecommerce.produto;

import br.com.zup.ecommerce.categoria.Categoria;
import br.com.zup.ecommerce.produto.caracteristica.Caracteristica;
import br.com.zup.ecommerce.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    @Min(1)
    private BigDecimal valor;
    @Column(nullable = false)
    @Min(0)
    private Integer quantidade;
    @Column(nullable = false)
    @Length(max = 1000)
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;

    @OneToMany( cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinColumn(name = "produto_id")
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @Deprecated
    public Produto() {}

    public Produto(@NotBlank String nome, @NotNull @Min(1) BigDecimal valor,
                   @NotNull Integer quantidade, @NotBlank String descricao,
                   @NotNull Categoria categoria , @NotNull @Size(min = 3) Set<Caracteristica> caracteristica, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.dataCriacao = LocalDateTime.now();
        this.categoria = categoria;
        this.caracteristicas = caracteristica;
        this.usuario = usuario;
    }
}
