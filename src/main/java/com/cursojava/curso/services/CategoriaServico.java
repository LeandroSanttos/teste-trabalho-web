package com.cursojava.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursojava.curso.entities.Categoria;
import com.cursojava.curso.repositories.CategoriaRepositorio;

@Service
public class CategoriaServico {

    @Autowired
    private CategoriaRepositorio repositorio;

    public List<Categoria> findAll() {
        return repositorio.findAll();
    }

    public Categoria findByID(Long id) {
        Optional<Categoria> obj = repositorio.findById(id);
        return obj.get();
    }
}
