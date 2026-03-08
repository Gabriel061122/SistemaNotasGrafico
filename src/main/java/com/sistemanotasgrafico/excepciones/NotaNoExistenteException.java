package com.sistemanotasgrafico.excepciones;

public class NotaNoExistenteException extends RuntimeException {
    public NotaNoExistenteException(String message) {
        super(message);
    }
}
