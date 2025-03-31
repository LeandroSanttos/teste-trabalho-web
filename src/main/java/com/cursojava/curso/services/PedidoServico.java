package com.cursojava.curso.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cursojava.curso.entities.Pedido;
import com.cursojava.curso.entities.Usuario;
import com.cursojava.curso.entities.enums.PedidoStatus;
import com.cursojava.curso.repositories.PedidoRepositorio;
import com.cursojava.curso.services.exceptions.DatabaseException;
import com.cursojava.curso.services.exceptions.ResourceNaoEncontradoException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoServico {
    
    @Autowired
    private PedidoRepositorio repositorio;

    public Pedido novoPedido(Long id, Instant momento, PedidoStatus pedidoStatus, Usuario client) {
        validarPedido(id, momento, pedidoStatus, client);
        Pedido novoPedido = new Pedido(id, momento, pedidoStatus, client);
        repositorio.save(novoPedido);
        return novoPedido;
    }

    public void validarPedido(Long id, Instant momento, PedidoStatus pedidoStatus, Usuario cliente) {
        if (momento == null) {
            throw new IllegalArgumentException("O momento do pedido não pode ser vazio.");
        }
        if (pedidoStatus == null) {
            throw new IllegalArgumentException("O status do pedido não pode ser vazio.");
        }
        if (cliente == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Cliente não pode ser vazio.");
        }
    }

    public List<Pedido> findAll() {
        return repositorio.findAll();
    }

    public Pedido findById(Long id) {
        Optional<Pedido> obj = repositorio.findById(id);
        return obj.get();
    }

    public Pedido insert(Pedido obj) {
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

    public Pedido update(Long id, Pedido obj) {
        try {
            Pedido entidade = repositorio.getReferenceById(id);
            updateData(entidade, obj);
            return repositorio.save(entidade);
        } catch (EntityNotFoundException e) {
            throw new ResourceNaoEncontradoException(id);
        }
    }

    public void updateData(Pedido entidade, Pedido obj) {
        if (obj.getPedidoStatus() != null) {
            entidade.setPedidoStatus(obj.getPedidoStatus());
        }
    }
}
