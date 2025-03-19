package com.cursojava.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursojava.curso.entities.ItemPedido;

public interface ItemPedidoRepositorio extends JpaRepository<ItemPedido, Long> {
    
}
