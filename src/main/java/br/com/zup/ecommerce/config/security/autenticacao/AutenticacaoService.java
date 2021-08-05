package br.com.zup.ecommerce.config.security.autenticacao;

import br.com.zup.ecommerce.usuario.Usuario;
import br.com.zup.ecommerce.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByEmail(username);
        if (usuario.isPresent())  return (UserDetails) usuario.get();
        throw new UsernameNotFoundException("Dados inválidos!");
    }
}
