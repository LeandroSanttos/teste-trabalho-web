package com.cursojava.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursojava.curso.entities.Categoria;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
	
}
