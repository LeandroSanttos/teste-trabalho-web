package com.cursojava.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursojava.curso.entities.Produto;
import com.cursojava.curso.repositories.ProdutoRepositorio;

@Service
public class ProdutoServico {

    @Autowired
    private ProdutoRepositorio repositorio;

    public List<Produto> findAll() {
        return repositorio.findAll();
    }

    public Produto findByID(Long id) {
        Optional<Produto> obj = repositorio.findById(id);
        return obj.get();
    }
}
