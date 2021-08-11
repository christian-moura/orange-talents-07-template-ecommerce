package br.com.zup.ecommerce.email;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
public interface EmailServer {
    void send(@NotBlank String titulo, @NotBlank String corpo, @NotBlank String remetente, @NotBlank String destinatario);
}
