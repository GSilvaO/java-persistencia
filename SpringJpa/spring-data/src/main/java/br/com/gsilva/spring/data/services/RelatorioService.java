package br.com.gsilva.spring.data.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.gsilva.spring.data.models.Funcionario;
import br.com.gsilva.spring.data.repositories.FuncionarioRepository;

@Service
public class RelatorioService {
	
	private Boolean system = false;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatorioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual acao de relatorio deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Buscar funcionario por nome");
			System.out.println("2 - Buscar funcionario por nome, salario e data contratacao");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				buscarFuncionarioPorNome(scanner);
				break;
			case 2:
				buscarFuncionarioNomeSalarioMaiorData(scanner);
				break;
			default:
				system = false;
				break;
			}
			
		}
	}
	
	private void buscarFuncionarioPorNome(Scanner scanner) {
		System.out.println("Por qual nome deseja pesquisar?");
		String nome = scanner.next();
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);
	}
	
	private void buscarFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Por qual nome deseja pesquisar?");
		String nome = scanner.next();
		System.out.println("Por qual salario deseja pesquisar?");
		Double salario = scanner.nextDouble();
		System.out.println("Por qual data de contratacao deseja pesquisar?");
		String data = scanner.next();
		
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> list = funcionarioRepository
				.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		
		list.forEach(System.out::println);
	}
}
