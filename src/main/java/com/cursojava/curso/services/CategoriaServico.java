package com.cursojava.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cursojava.curso.entities.Categoria;
import com.cursojava.curso.repositories.CategoriaRepositorio;
import com.cursojava.curso.services.exceptions.DatabaseException;
import com.cursojava.curso.services.exceptions.ResourceNaoEncontradoException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaServico {

    @Autowired
    private CategoriaRepositorio repositorio;

    public Categoria cadastrarCategoria(Long id, String nome) {
        validarCategoria(id, nome);
        Categoria novaCategoria = new Categoria(id, nome);
        repositorio.save(novaCategoria);
        return novaCategoria;
    }

    public void validarCategoria(Long id, String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
    }

    public List<Categoria> findAll() {
        return repositorio.findAll();
    }

    public Categoria findByID(Long id) {
        Optional<Categoria> obj = repositorio.findById(id);
        return obj.get();
    }

    public Categoria insert(Categoria obj) {
        return repositorio.save(obj);
    }

    public void delete(Long id) {
        try {
            repositorio.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Categoria update(Long id, Categoria obj) {
        try {
            Categoria entidade = repositorio.getReferenceById(id);
            updateData(entidade, obj);
            return repositorio.save(obj);
        } catch (EntityNotFoundException e) {
            throw new ResourceNaoEncontradoException(id);
        }
    }

    public void updateData(Categoria entidade, Categoria obj) {
        entidade.setNome(obj.getNome());
    }
}
