package com.cursojava.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursojava.curso.entities.Produto;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {
    
}
