package com.cursojava.curso.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.cursojava.curso.entities.Categoria;
import com.cursojava.curso.entities.ItemPedido;
import com.cursojava.curso.entities.Pagamento;
import com.cursojava.curso.entities.Pedido;
import com.cursojava.curso.entities.Produto;
import com.cursojava.curso.entities.Usuario;
import com.cursojava.curso.entities.enums.PedidoStatus;
import com.cursojava.curso.repositories.ItemPedidoRepositorio;
import com.cursojava.curso.repositories.PedidoRepositorio;
import com.cursojava.curso.repositories.ProdutoRepositorio;
import com.cursojava.curso.services.CategoriaServico;
import com.cursojava.curso.services.PedidoServico;
import com.cursojava.curso.services.ProdutoServico;
import com.cursojava.curso.services.UsuarioServico;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner{

	@Autowired
	private PedidoRepositorio pedidoRepositorio;

	@Autowired
	private ProdutoRepositorio produtoRepositorio;

	@Autowired
	private ItemPedidoRepositorio itemPedidoRepositorio;

	@Autowired
	private UsuarioServico usuarioServico;

	@Autowired
	private ProdutoServico produtoServico;

	@Autowired
	private PedidoServico pedidoServico;

	@Autowired
	private CategoriaServico categoriaServico;

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = categoriaServico.cadastrarCategoria(null, "Etrônicos");
		Categoria cat2 = categoriaServico.cadastrarCategoria(null, "Livros");
		Categoria cat3 = categoriaServico.cadastrarCategoria(null, "Computadores");

		Produto prdt1 = produtoServico.cadastrarProduto(null, "O Senhor dos Anéis", "História de aventura", 90.5, "");
		Produto prdt2 = produtoServico.cadastrarProduto(null, "Smart TV", "TV 60 polegadas", 2190.0, "");
		Produto prdt3 = produtoServico.cadastrarProduto(null, "Macbook Pro", "Computador portátil", 1250.0, "");
		Produto prdt4 = produtoServico.cadastrarProduto(null, "PC Gamer", "Computador potente para jogos", 1200.0, "");
		Produto prdt5 = produtoServico.cadastrarProduto(null, "Harry Potter", "Livro de aventura", 100.99, "");

		produtoServico.addCategoria(prdt1, cat2);
		produtoServico.addCategoria(prdt2, cat1);
		produtoServico.addCategoria(prdt3, cat3);
		produtoServico.addCategoria(prdt4, cat1);
		//produtoServico.addCategoria(prdt4, cat3);
		produtoServico.addCategoria(prdt5, cat2);

		Usuario u1 = usuarioServico.cadastrarUsuario(0L, "Maria Brown", "maria@gmail.com", "999999999", "12345");
		Usuario u2 = usuarioServico.cadastrarUsuario(0L, "Alex Green", "alex@gmail.com", "988888888", "123456");
		Usuario u3 = usuarioServico.cadastrarUsuario(0L, "Bob Brawn", "bob@gmail.com", "977777777", "123456");

		Pedido p1 = pedidoServico.novoPedido(null, Instant.parse("2025-02-14T19:53:07Z"),PedidoStatus.PAGO, u1);
		Pedido p2 = pedidoServico.novoPedido(null, Instant.parse("2025-02-15T03:42:10Z"),PedidoStatus.AGUARDANDO_PAGAMENTO, u2);
		Pedido p3 = pedidoServico.novoPedido(null, Instant.parse("2025-02-16T15:21:22Z"),PedidoStatus.AGUARDANDO_PAGAMENTO, u1);

		ItemPedido ipdd1 = new ItemPedido(p1, prdt1, 2, prdt1.getPreco());
		ItemPedido ipdd2 = new ItemPedido(p1, prdt3, 1, prdt3.getPreco());
		ItemPedido ipdd3 = new ItemPedido(p2, prdt3, 2, prdt3.getPreco());
		ItemPedido ipdd4 = new ItemPedido(p3, prdt5, 2, prdt5.getPreco());

		itemPedidoRepositorio.saveAll(Arrays.asList(ipdd1, ipdd2, ipdd3, ipdd4));

		Pagamento pagamento1 = new Pagamento(null, Instant.parse("2025-02-14T21:53:07Z"), p1);
		p1.setPagamento(pagamento1);

		pedidoRepositorio.save(p1);
	}
	
	
}
