package com.cursojava.curso.negocios;

import java.time.Instant;

import com.cursojava.curso.entities.Usuario;
import com.cursojava.curso.entities.enums.PedidoStatus;
import com.cursojava.curso.negocios.exceptions.PedidoNaoCadastradoException;

public class PedidoNegocio {
    public void validarPedido(Long id, Instant momento, PedidoStatus pedidoStatus, Usuario cliente) throws PedidoNaoCadastradoException {
        if (cliente == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Cliente não pode ser vazio.");
        }
    }
}
