package com.cursojava.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursojava.curso.entities.Pedido;

public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
    
}
