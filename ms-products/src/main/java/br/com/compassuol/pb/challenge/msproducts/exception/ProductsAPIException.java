package br.com.compassuol.pb.challenge.msproducts.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ProductsAPIException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public ProductsAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

}
