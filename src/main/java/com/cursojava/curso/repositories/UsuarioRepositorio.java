package com.cursojava.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursojava.curso.entities.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	
}
