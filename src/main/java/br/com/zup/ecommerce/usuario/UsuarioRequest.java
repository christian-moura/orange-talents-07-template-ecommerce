package br.com.zup.ecommerce.usuario;

import br.com.zup.ecommerce.validations.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    @JsonProperty
    @UniqueValue(entity = Usuario.class, attribute = "email", message = "Email de usuário indisponível. Informe outro.")
    @NotBlank
    private String email;
    @JsonProperty
    @NotBlank
    @Size(min = 6, message = "Senha deve conter 6 ou mais caracteres.")
    private String senha;

    public Usuario toUsuario() {
       return new Usuario(this.email, new SenhaLimpa(this.senha));
    }
}
