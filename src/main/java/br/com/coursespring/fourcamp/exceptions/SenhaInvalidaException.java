package br.com.coursespring.fourcamp.exceptions;

public class SenhaInvalidaException extends RuntimeException{
    public SenhaInvalidaException() {
        super("Senha invalida");
    }
}
