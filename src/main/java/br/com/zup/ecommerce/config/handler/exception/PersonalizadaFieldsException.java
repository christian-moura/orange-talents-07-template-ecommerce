package br.com.zup.ecommerce.config.handler.exception;

import org.springframework.http.HttpStatus;

public class PersonalizadaFieldsException extends RuntimeException {

    private String campo;
    private HttpStatus status;

    public PersonalizadaFieldsException(HttpStatus status, String campo, String message ) {
        super(message);
        this.status = status;
        this.campo = campo;
    }

    public PersonalizadaFieldsException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public String getCampo() {
        return campo;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
