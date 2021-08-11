package br.com.zup.ecommerce.email;

import br.com.zup.ecommerce.compra.Compra;
import br.com.zup.ecommerce.produto.pergunta.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Email {

    @Autowired
    private EmailServer emailServer;


    public void enviaPergunta(Pergunta pergunta){
        String titulo = "Nova Pergunta - Produto "+pergunta.getProduto().getNome();
        String corpo = pergunta.getTitulo()+"\n";
        emailServer.send(titulo,corpo,pergunta.getUsuario().getUsername(),pergunta.getProduto().getUsuario().getUsername());
    }

    public void novaCompra(Compra compra) {
        String titulo = "Nova Compra - Produto "+compra.getProduto().getNome();
        String corpo = "Quantidade: "+compra.getQuantidade()+"\n"+
        "Valor Produto: "+compra.getValor()+"\n"+
        "Status: "+compra.getStatusCompra();
        emailServer.send(titulo,corpo, compra.getUsuario().getUsername(),compra.getProduto().getUsuario().getUsername());
    }

    public void compraFinalizada(Compra compra) {
        String titulo = "Nova Compra - Produto "+compra.getProduto().getNome();
        String corpo = "Quantidade: "+compra.getQuantidade()+"\n"+
                "Valor Produto: "+compra.getValor()+"\n"+
                "Status: "+compra.getStatusCompra()+"\n"+
                "Meio de pagamento: " +compra.getGatewayPagamento().getGateway();
        emailServer.send(titulo,corpo, "pagamentos@mercadolivre.email.com",compra.getProduto().getUsuario().getUsername());
    }

    public void falhaNoPagamento(Compra compra) {
        String titulo = "Falha no pagamento da Compra: "+compra.getId()+" - Produto "+compra.getProduto().getNome();
        String corpo = "Quantidade: "+compra.getQuantidade()+"\n"+
                "Valor Produto: "+compra.getValor()+"\n"+
                "Status Compra: "+compra.getStatusCompra()+"\n"+
                "Meio de tentativa de pagamento: " +compra.getGatewayPagamento().getGateway()+"\n"+
                "Tente novamente em: " +compra.getGatewayPagamento().getGatewayUri(compra.getId());
        emailServer.send(titulo,corpo, "pagamentos@mercadolivre.email.com",compra.getUsuario().getUsername());
    }
}
