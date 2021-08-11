package br.com.zup.ecommerce.compra.transacao;

import br.com.zup.ecommerce.compra.Compra;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private StatusTransacao status;
    @Column(nullable = false)
    private  String idTransacaoGateway;
    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.EAGER)
    private Compra compra;

    @Deprecated
    public Transacao() {
    }

    public Transacao(StatusTransacao status, String idTransacaoGateway, Compra compra) {
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.dataCriacao = LocalDateTime.now();
        this.compra = compra;
    }

    public Boolean tracacaoSucedida(){
        return this.status.equals(StatusTransacao.SUCESSO);
    }
}
