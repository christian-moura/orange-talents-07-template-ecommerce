package br.com.zup.ecommerce.config.handler;

import br.com.zup.ecommerce.config.handler.exception.PersonalizadaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class Handler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> MethodArgumentNotValid(MethodArgumentNotValidException e){
        List<Error> erros = e.getFieldErrors().stream().map(Error::new).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(PersonalizadaException.class)
    public ResponseEntity<Error> PersonalizadaException(PersonalizadaException e){
        Error error = new Error(e.getCampo(), e.getMessage() );
        return ResponseEntity.status(e.getStatus()).body(error);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> ResponseStatus(ResponseStatusException e){
        Error error = new Error(e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(error);
    }
}
