package br.com.gsilva.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.gsilva.spring.data.services.CrudCargoService;
import br.com.gsilva.spring.data.services.CrudFuncionarioService;
import br.com.gsilva.spring.data.services.CrudUnidadeService;
import br.com.gsilva.spring.data.services.RelatorioService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
	private Boolean system = true;
	
	private final CrudCargoService cargoService;
	private final CrudUnidadeService unidadeService;
	private final CrudFuncionarioService funcionarioService;
	private final RelatorioService relatorioService;
	
	// Fazendo a injeção de dependência
	public SpringDataApplication(CrudCargoService cargoService, CrudUnidadeService unidadeService,
			CrudFuncionarioService funcionarioService, RelatorioService relatorioService) {
		this.cargoService = cargoService;
		this.unidadeService = unidadeService;
		this.funcionarioService = funcionarioService;
		this.relatorioService = relatorioService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		while(system) {
			System.out.println("Qual acao voce quer executar?");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade");
			System.out.println("4 - Relatorio");
			
			Integer function = scanner.nextInt();

			switch (function) {
				case 1:
					cargoService.inicial(scanner);
					break;
				case 2:
					funcionarioService.inicial(scanner);
					break;
				case 3:
					unidadeService.inicial(scanner);
					break;
				case 4:
					relatorioService.inicial(scanner);
					break;
				default:
					System.out.println("Finalizando");
					system = false;
					break;
			}
		}
	}
}
