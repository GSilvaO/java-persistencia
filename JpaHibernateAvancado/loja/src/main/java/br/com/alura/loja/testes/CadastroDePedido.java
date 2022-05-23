package br.com.alura.loja.testes;

import java.math.BigDecimal;import java.util.List;

import javax.persistence.EntityManager;
import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.utils.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		Produto produto = produtoDao.buscarPorId(1l);
		Produto produto2 = produtoDao.buscarPorId(2l);
		Produto produto3 = produtoDao.buscarPorId(3l);
		Cliente cliente = clienteDao.buscarPorId(1l);
		
		em.getTransaction().begin();
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(5, pedido, produto));
		pedido.adicionarItem(new ItemPedido(2, pedido, produto2));
		pedido.adicionarItem(new ItemPedido(3, pedido, produto3));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("VALOR TOTAL VENDIDO: " + totalVendido);
		
		List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
		relatorio.forEach(System.out::println);
	}
	
	// Este método se faz necessário nas classes de teste pois o banco de dados
	// está configurado para não persistir os dados de forma permanente
	private static void popularBancoDeDados() {
		Categoria eletrodomesticos = new Categoria("ELETRODOMESTICO");
		Produto geladeira = new Produto("Consul 3000", "Show", new BigDecimal("8000"), eletrodomesticos);
		Produto fogao = new Produto("Fogão", "Show", new BigDecimal("1500"), eletrodomesticos);
		Produto lavadora = new Produto("ElectroluX", "Show", new BigDecimal("2000"), eletrodomesticos);
		Cliente cliente = new Cliente("Gabriel", "123456");
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(eletrodomesticos);
		produtoDao.cadastrar(geladeira);
		produtoDao.cadastrar(fogao);
		produtoDao.cadastrar(lavadora);
		clienteDao.cadastrar(cliente);
		
		em.getTransaction().commit();
		em.close();
	}

}
