package br.com.zup.ecommerce.config.handler;

import br.com.zup.ecommerce.config.handler.exception.PersonalizadaFieldsException;
import br.com.zup.ecommerce.config.handler.exception.PersonalizadaSingleMessageException;
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
    public ResponseEntity<List<ErrorFieldsBody>> MethodArgumentNotValid(MethodArgumentNotValidException e){
        List<ErrorFieldsBody> erros = e.getFieldErrors().stream().map(ErrorFieldsBody::new).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(PersonalizadaFieldsException.class)
    public ResponseEntity<ErrorFieldsBody> PersonalizadaFields(PersonalizadaFieldsException e){
        ErrorFieldsBody error = new ErrorFieldsBody(e.getCampo(), e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(error);
    }
    @ExceptionHandler(PersonalizadaSingleMessageException.class)
    public ResponseEntity<ErrorSingleMessage> PersonalizadaSingleMessage(PersonalizadaSingleMessageException e){
        ErrorSingleMessage error = new ErrorSingleMessage(e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(error);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> ResponseStatus(ResponseStatusException e){
        ErrorFieldsBody error = new ErrorFieldsBody(e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(error);
    }
}
