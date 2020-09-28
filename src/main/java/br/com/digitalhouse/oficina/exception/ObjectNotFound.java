package br.com.digitalhouse.oficina.exception;

public class ObjectNotFound extends RuntimeException{

    public ObjectNotFound(String errorMsg) {
        super(errorMsg);
    }
}
