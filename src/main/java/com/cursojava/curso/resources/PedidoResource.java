package com.cursojava.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursojava.curso.entities.Pedido;
import com.cursojava.curso.services.PedidoServico;

@RestController
@RequestMapping(value = "pedidos")
public class PedidoResource {
    
    @Autowired
    private PedidoServico servico;

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> list = servico.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id) {
        Pedido obj = servico.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
