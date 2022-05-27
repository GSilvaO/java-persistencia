package br.com.gsilva.spring.data.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.gsilva.spring.data.models.Cargo;
import br.com.gsilva.spring.data.models.Funcionario;
import br.com.gsilva.spring.data.models.Unidade;
import br.com.gsilva.spring.data.repositories.CargoRepository;
import br.com.gsilva.spring.data.repositories.FuncionarioRepository;
import br.com.gsilva.spring.data.repositories.UnidadeRepository;

@Service
public class CrudFuncionarioService {
	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final CargoRepository cargoRepository;
	private final FuncionarioRepository funcionarioRepository;
	private final UnidadeRepository unidadeRepository;
	
	
	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, 
			CargoRepository cargoRepository, UnidadeRepository unidadeRepository) {
		this.cargoRepository = cargoRepository;
		this.funcionarioRepository = funcionarioRepository;
		this.unidadeRepository = unidadeRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual acao de funcionario deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
			
		}
		
	}
	
	private void salvar(Scanner scanner) {
		System.out.println("Digite o nome");
        String nome = scanner.next();
        System.out.println("Digite o cpf");
        String cpf = scanner.next();
        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();
        System.out.println("Digite a data de contracao (dd/mm/yyyy)");
        String dataContratacao = scanner.next();
        System.out.println("Digite o ID do cargo");
        Integer cargoId = scanner.nextInt();

        List<Unidade> unidades = unidade(scanner);
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        
        Funcionario funcionario = new Funcionario(nome, cpf, salario,
        		LocalDate.parse(dataContratacao, formatter), cargo.get());
        
        funcionario.setUnidades(unidades);

        funcionarioRepository.save(funcionario);
        System.out.println("Salvo");
	}
	
	private List<Unidade> unidade(Scanner scanner) {
        Boolean isTrue = true;
        List<Unidade> unidades = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o ID da unidade (Para sair digite 0)");
            Integer unidadeId = scanner.nextInt();

            if(unidadeId != 0) {
                Optional<Unidade> unidade = unidadeRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }

        return unidades;
    }
	
	private void atualizar(Scanner scanner) {
		System.out.println("Digite o id");
        Integer id = scanner.nextInt();
        System.out.println("Digite o nome");
        String nome = scanner.next();
        System.out.println("Digite o cpf");
        String cpf = scanner.next();
        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();
        System.out.println("Digite a data de contracao");
        String dataContratacao = scanner.next();
        System.out.println("Digite o ID do cargo");
        Integer cargoId = scanner.nextInt();

        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        
        Funcionario funcionario = new Funcionario(nome, cpf, salario,
        		LocalDate.parse(dataContratacao, formatter), cargo.get());
        funcionario.setId(id);
        
        funcionarioRepository.save(funcionario);
        System.out.println("Alterado");
	}
	
	private void visualizar() {
		Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("Deletado");
	}
}
