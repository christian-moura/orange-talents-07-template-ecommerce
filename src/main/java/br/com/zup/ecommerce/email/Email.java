package br.com.zup.ecommerce.email;

import br.com.zup.ecommerce.produto.pergunta.Pergunta;
import org.springframework.stereotype.Service;

@Service
public class Email {
    public void enviaPergunta(Pergunta pergunta){
        System.out.println("Nova Pergunta - Produto "+pergunta.getProduto().getNome());
        System.out.println(pergunta.getTitulo());
        System.out.println("Interessado: "+pergunta.getUsuario().getUsername());
        System.out.println("Destinatário: "+pergunta.getProduto().getUsuario().getUsername());
    }
}
