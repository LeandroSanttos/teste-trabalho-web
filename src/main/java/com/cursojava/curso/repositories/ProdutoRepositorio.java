package com.cursojava.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursojava.curso.entities.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {
    
}
