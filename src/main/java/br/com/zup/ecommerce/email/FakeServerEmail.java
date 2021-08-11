package br.com.zup.ecommerce.email;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
//@Profile("dev")
public class FakeServerEmail implements EmailServer{
    @Override
    public void send(String titulo, String corpo, String remetente, String destinatario) {
        System.out.println("Título: "+titulo);
        System.out.println("Remetente: "+remetente);
        System.out.println("Mensagem: "+corpo);
        System.out.println("Destinatário: "+destinatario);
    }
}
