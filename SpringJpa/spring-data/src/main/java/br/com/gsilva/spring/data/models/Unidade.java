package br.com.gsilva.spring.data.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "unidades")
public class Unidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String descricao;
	private String endereco;
	
	@ManyToMany(mappedBy = "unidades", fetch = FetchType.EAGER)
	private List<Funcionario> funcionarios = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.getFuncionarios().addAll(funcionarios);
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.getFuncionarios().add(funcionario);
	}

	@Override
	public String toString() {
		return "Unidade [id=" + id + ", descricao=" + descricao + ", endereco=" + endereco + ", funcionarios="
				+ funcionarios + "]";
	}
	
	

}
