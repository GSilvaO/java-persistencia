package br.com.gsilva.spring.data.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;
	private String cpf;
	private Double salario;
	private LocalDate dataContratacao;
	
	@ManyToOne
	private Cargo cargo;
	
	@ManyToMany
	@JoinTable(name = "funcionarios_unidades", joinColumns = {
			@JoinColumn(name = "funcionarios_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "unidades_id") })
	private List<Unidade> unidades = new ArrayList<>();
	
	public Funcionario() { }

	public Funcionario(String nome, String cpf, 
			Double salario, LocalDate dataContratacao, Cargo cargo) {
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
		this.dataContratacao = dataContratacao;
		this.cargo = cargo;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Unidade> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}
	
	@Override
	public String toString() {
		return "Funcionario: " + "id:" + id + "| nome:'" + nome + "| cpf:" + cpf + "| salario:" + salario
				+ "| dataContratacao:" + dataContratacao + "| cargo:" + cargo.getDescricao();
	}

}
