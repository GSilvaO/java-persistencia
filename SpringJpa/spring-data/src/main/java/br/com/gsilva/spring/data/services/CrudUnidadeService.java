package br.com.gsilva.spring.data.services;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.gsilva.spring.data.models.Unidade;
import br.com.gsilva.spring.data.repositories.UnidadeRepository;

@Service
public class CrudUnidadeService {
	
	private final UnidadeRepository unidadeRepository;
	private Boolean system = true;
	
	public CrudUnidadeService(UnidadeRepository unidadeRepository) {
		this.unidadeRepository = unidadeRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual acao de unidade deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			
			int action = scanner.nextInt();
			
			switch(action) {
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
	
	public void salvar(Scanner scanner) {
		System.out.println("Descricao da unidade");
		String descricao = scanner.next();
		System.out.println("Endereco da unidade");
		String endereco = scanner.next();
		
		Unidade unidade = new Unidade();
		
		unidade.setDescricao(descricao);
		unidade.setEndereco(endereco);
		unidadeRepository.save(unidade);
		System.out.println("Salvo");
	}
	
	public void atualizar(Scanner scanner) {
		System.out.println("Digite o ID");
        Integer id = scanner.nextInt();
        System.out.println("Digite o nome da unidade");
        String nome = scanner.next();
        System.out.println("Digite o endereco");
        String endereco = scanner.next();

        Unidade unidade = new Unidade();
        
        unidade.setId(id);
        unidade.setDescricao(nome);
        unidade.setEndereco(endereco);

        unidadeRepository.save(unidade);
        System.out.println("Alterado");
	}
	
	private void visualizar() {
		Iterable<Unidade> unidades = unidadeRepository.findAll();
		unidades.forEach(unidade -> System.out.println(unidade));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		unidadeRepository.deleteById(id);
		System.out.println("Deletado");
	}

}
