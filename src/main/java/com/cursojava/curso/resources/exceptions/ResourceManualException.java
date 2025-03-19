package com.cursojava.curso.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cursojava.curso.services.exceptions.DatabaseException;
import com.cursojava.curso.services.exceptions.ResourceNaoEncontradoException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceManualException {

    @ExceptionHandler(ResourceNaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> ResourceNaoEncontradoException(ResourceNaoEncontradoException e, HttpServletRequest request) {
        String erro = "Recurso não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), erro, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ErroPadrao> database(DatabaseException e, HttpServletRequest request) {
        String erro = "Erro na base de dados";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), erro, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
