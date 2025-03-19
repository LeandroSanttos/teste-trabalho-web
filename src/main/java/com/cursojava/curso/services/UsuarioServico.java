package com.cursojava.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cursojava.curso.entities.Usuario;
import com.cursojava.curso.repositories.UsuarioRepositorio;
import com.cursojava.curso.services.exceptions.DatabaseException;
import com.cursojava.curso.services.exceptions.ResourceNaoEncontradoException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepositorio repositorio;

    public List<Usuario> findAll() {
        return repositorio.findAll();
    }

    public Usuario findByID(Long id) {
        Optional<Usuario> obj = repositorio.findById(id);
        return obj.orElseThrow(() -> new ResourceNaoEncontradoException(id));
    }

    public Usuario insert(Usuario obj) {
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

    public Usuario update(Long id, Usuario obj) {
        try {
            Usuario entidade = repositorio.getReferenceById(id);
            updateData(entidade, obj);
            return repositorio.save(entidade);
        } catch (EntityNotFoundException e) {
            throw new ResourceNaoEncontradoException(id);
        }
    }

    private void updateData(Usuario entidade, Usuario obj) {
        entidade.setNome(obj.getNome());
        entidade.setEmail(obj.getEmail());
        entidade.setTelefone(obj.getTelefone());
    }
}
