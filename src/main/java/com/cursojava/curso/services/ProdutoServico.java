package com.cursojava.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursojava.curso.entities.Categoria;
import com.cursojava.curso.entities.Produto;
import com.cursojava.curso.repositories.CategoriaRepositorio;
import com.cursojava.curso.repositories.ProdutoRepositorio;
import com.cursojava.curso.services.exceptions.DatabaseException;
import com.cursojava.curso.services.exceptions.ResourceNaoEncontradoException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoServico {

    @Autowired
    private ProdutoRepositorio repositorio;

    @Autowired
    private CategoriaRepositorio repositorioCategoria;

    public Produto cadastrarProduto(Long id, String nome, String descricao, Double preco, String img_URL) {
        validarProduto(id, nome, descricao, preco, img_URL);
        Produto novoProduto = new Produto(id, nome, descricao, preco, img_URL);
        repositorio.save(novoProduto);
        return novoProduto;
    }

    public void validarProduto(Long id, String nome, String descricao, Double preco, String img_URL) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição do produto não pode ser vazia.");
        }
        if (preco == null || preco <= 0) {
            throw new IllegalArgumentException("O preco do produto não pode ser 0 ou menor.");
        }
    }

    @Transactional
    public void addCategoria(Long idProduto, Long idCategoria) {
        Produto produto = repositorio.findById(idProduto).orElseThrow(()-> new RuntimeException("Produto não encontrado"));
        Categoria categoria = repositorioCategoria.findById(idCategoria).orElseThrow(()-> new RuntimeException("Categoria não encontrado"));
        if (!produto.getCategorias().contains(categoria)) {
            produto.getCategorias().add(categoria);
            repositorio.save(produto);
        }
    }

    public List<Produto> findAll() {
        return repositorio.findAll();
    }

    public Produto findByID(Long id) {
        Optional<Produto> obj = repositorio.findById(id);
        return obj.get();
    }

    public Produto insert(Produto obj) {
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

    public Produto update(Long id, Produto obj) {
        try {
            Produto entidade = repositorio.getReferenceById(id);
            updateData(entidade, obj);
            return repositorio.save(entidade);
        } catch (EntityNotFoundException e) {
            throw new ResourceNaoEncontradoException(id);
        }
    }

    public void updateData(Produto entidade, Produto obj) {
        entidade.setNome(obj.getNome());
        entidade.setDescricao(obj.getDescricao());
        entidade.setPreco(obj.getPreco());
        entidade.setImg_URL(obj.getDescricao());
    }
}
