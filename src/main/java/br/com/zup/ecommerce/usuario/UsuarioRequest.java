package br.com.zup.ecommerce.usuario;

import br.com.zup.ecommerce.validations.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class UsuarioRequest {

    @JsonProperty
    @UniqueValue(entity = Usuario.class, attribute = "login")
    @NotBlank
    private String login;
    @JsonProperty
    @NotBlank
    private String senha;

    public Usuario toUsuario() {
       return new Usuario(this.login,this.senha);
    }
}
