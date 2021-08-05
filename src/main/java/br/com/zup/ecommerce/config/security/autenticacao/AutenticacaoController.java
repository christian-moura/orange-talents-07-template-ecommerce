package br.com.zup.ecommerce.config.security.autenticacao;

import br.com.zup.ecommerce.config.security.token.LoginRequest;
import br.com.zup.ecommerce.config.security.token.TokenResponse;
import br.com.zup.ecommerce.config.security.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {


    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    @Autowired
    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?>autenticar( @RequestBody @Valid LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken dadosLogin = loginRequest.CredentialsAuthenticationToken();
        try{
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
        }
        catch (AuthenticationException e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
