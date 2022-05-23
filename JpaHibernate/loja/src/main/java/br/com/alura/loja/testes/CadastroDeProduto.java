package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.modelo.Produto;

public class CadastroDeProduto {

	public static void main(String[] args) {
		Produto geladeira = new Produto();
		geladeira.setNome("Consul 3000");
		geladeira.setDescricao("Show");
		geladeira.setPreco(new BigDecimal("8000"));
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(geladeira);
		em.getTransaction().commit();
		em.close();
	}

}
