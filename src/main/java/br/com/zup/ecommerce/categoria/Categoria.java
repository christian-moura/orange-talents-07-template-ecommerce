package br.com.zup.ecommerce.categoria;

import br.com.zup.ecommerce.produto.Produto;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String nome;
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoriaMae;

    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos;

    @Deprecated
    public Categoria() { }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(String nome, Categoria categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }
}
