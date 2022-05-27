package br.com.gsilva.spring.data.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.gsilva.spring.data.models.Funcionario;

public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer> {
	
	List<Funcionario> findByNome(String nome);
	
	//Faz um JOIN com a tabela de cargo atraves do atributo cargo.descricao
	List<Funcionario> findByCargoDescricao(String descricao);
	
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome "
			+ "AND f.salario >= :salario AND f.dataContratacao = :dataContratacao")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate dataContratacao);
	
	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :dataContratacao",
			nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(LocalDate dataContratacao);
}
