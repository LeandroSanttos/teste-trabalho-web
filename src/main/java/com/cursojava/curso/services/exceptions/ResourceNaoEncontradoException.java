package com.cursojava.curso.services.exceptions;

public class ResourceNaoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceNaoEncontradoException(Object id) {
        super("Rescurso não encontrado. Id " + id);
    }
}
