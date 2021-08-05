package br.com.zup.ecommerce.config.security.autenticacao;

import br.com.zup.ecommerce.config.security.token.TokenService;
import br.com.zup.ecommerce.usuario.Usuario;
import br.com.zup.ecommerce.usuario.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter  extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(httpServletRequest);
        Boolean valido = tokenService.isTokenValido(token);
        if(valido) autorizarCliente(token);
        filterChain.doFilter(httpServletRequest,  httpServletResponse);
    }

    private void autorizarCliente(String token) {
        Usuario usuario = usuarioRepository.findById(tokenService.getIdUsuario(token)).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer "))  return null;
        return token.substring(7, token.length());
    }
}
