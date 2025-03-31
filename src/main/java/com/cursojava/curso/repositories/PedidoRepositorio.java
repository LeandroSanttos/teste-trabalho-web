package com.cursojava.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursojava.curso.entities.Pedido;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
    
}
