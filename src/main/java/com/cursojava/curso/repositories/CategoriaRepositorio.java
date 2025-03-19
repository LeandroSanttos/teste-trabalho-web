package com.cursojava.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursojava.curso.entities.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
	
}
