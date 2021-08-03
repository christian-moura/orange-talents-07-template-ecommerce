package br.com.zup.ecommerce.categoria;

import javax.persistence.*;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String nome;
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoriaMae;

    public Categoria() { }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(String nome, Categoria categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }
}
