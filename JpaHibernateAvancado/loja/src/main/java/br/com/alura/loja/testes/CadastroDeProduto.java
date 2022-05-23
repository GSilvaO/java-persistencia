package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.utils.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		List<Produto> produto = produtoDao.buscarPorParametrosComCriteria("Consul 3000", new BigDecimal("8000"), null);
		
		produto.forEach(p -> System.out.println(p.getNome()));
		
		/*
		 * Produto p = produtoDao.buscarPorId(1l);
		 * 
		 * List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("ELETRODOMESTICO");
		 * BigDecimal preco = produtoDao.buscarPrecoDoProdutoPorNome("Consul 3000");
		 * 
		 * System.out.println("Preço do Produto" + preco);
		 * 
		 * todos.forEach(p2 -> System.out.println(p2.getNome()));
		 */
		
		
	}

	private static void cadastrarProduto() {
		Categoria eletrodomesticos = new Categoria("ELETRODOMESTICO");
		Produto geladeira = new Produto("Consul 3000", "Show", new BigDecimal("8000"), eletrodomesticos);
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(eletrodomesticos);
		produtoDao.cadastrar(geladeira);
		
		em.getTransaction().commit();
		em.close();
	}

}
