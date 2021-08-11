package br.com.zup.ecommerce.config.handler.exception;

import org.springframework.http.HttpStatus;

public class PersonalizadaSingleMessageException extends RuntimeException {

    private HttpStatus status;

    public PersonalizadaSingleMessageException(HttpStatus status,  String message ) {
        super(message);
        this.status = status;

    }
    public HttpStatus getStatus() {
        return status;
    }


}
