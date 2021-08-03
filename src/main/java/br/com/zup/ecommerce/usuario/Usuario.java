package br.com.zup.ecommerce.usuario;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    public Usuario() { }

    public Usuario(String email, SenhaLimpa senha) {
        this.email = email;
        this.senha = senha.hash();
        this.dataCadastro = LocalDateTime.now();
    }
}
