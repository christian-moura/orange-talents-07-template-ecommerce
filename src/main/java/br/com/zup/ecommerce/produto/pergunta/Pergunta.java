package br.com.zup.ecommerce.produto.pergunta;

import br.com.zup.ecommerce.produto.Produto;
import br.com.zup.ecommerce.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    private Produto produto;


    @Deprecated
    public Pergunta() {
    }

    public Pergunta(@NotBlank  String titulo, @NotNull Usuario usuario, @NotNull Produto produto) {
        this.titulo = titulo;
        this.dataCriacao = LocalDateTime.now();
        this.usuario = usuario;
        this.produto = produto;
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }
}
