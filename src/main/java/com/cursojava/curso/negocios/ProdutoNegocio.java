package com.cursojava.curso.negocios;

import com.cursojava.curso.entities.Produto;
import com.cursojava.curso.negocios.exceptions.ProdutoNaoCadastradoException;

public class ProdutoNegocio {

    public Produto novoProduto(Long id, String nome, String descricao, Double preco, String img_URL) throws ProdutoNaoCadastradoException {
        validarProduto(id, nome, descricao, preco, img_URL);
        Produto novoProduto = new Produto(id, nome, descricao, preco, img_URL);
        return novoProduto;
    }

    public void validarProduto(Long id, String nome, String descricao, Double preco, String img_URL) throws ProdutoNaoCadastradoException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição do produto não pode ser vazia.");
        }
        if (preco <= 0) {
            throw new IllegalArgumentException("O preco do produto não pode ser vazio.");
        }
    }
}
