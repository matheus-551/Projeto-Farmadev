package br.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Funcionario {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String NomeCompleto;
	@Column(unique = true, nullable = false, length = 11)
	private String cpf;
	@Column(unique = true, nullable = false)
	private String IdentificacaoContrato;
	private Endereco endereco;
	@OneToOne
	private Acesso acesso;
	
	public Funcionario() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return NomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		NomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getIdentificacaoContrato() {
		return IdentificacaoContrato;
	}

	public void setIdentificacaoContrato(String identificacaoContrato) {
		IdentificacaoContrato = identificacaoContrato;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Acesso getAcesso() {
		return acesso;
	}

	public void setAcesso(Acesso acesso) {
		this.acesso = acesso;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", NomeCompleto=" + NomeCompleto + ", cpf=" + cpf + ", IdentificacaoContrato="
				+ IdentificacaoContrato + ", endereco=" + endereco + ", acesso=" + acesso + "]";
	}
}