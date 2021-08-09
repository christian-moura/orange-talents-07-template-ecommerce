package br.com.zup.ecommerce.produto.imagem;

import br.com.zup.ecommerce.produto.Produto;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @URL
    private String url;
    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(String url, Produto produto) {
        this.url = url;
        this.produto = produto;
    }

    public String getUrl() {
        return url;
    }
}
