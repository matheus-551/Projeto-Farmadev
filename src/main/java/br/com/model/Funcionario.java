package br.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Funcionario {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	@NotBlank(message = "Digite um nome válido")
	private String NomeCompleto;
	
	@Column(unique = true, nullable = false, length = 11)
	@CPF(message = "CPF inválido")
	private String cpf;
	
	@Column(unique = true, nullable = false)
	@Pattern(regexp="[F,M,V]{3}\\d{4}", message="Contrato inválido")
	private String contrato;
	
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

	public String getContrato() {
		return contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
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
		return "Funcionario [id=" + id + ", NomeCompleto=" + NomeCompleto + ", cpf=" + cpf + ", contrato="
				+ contrato + ", endereco=" + endereco + ", acesso=" + acesso + "]";
	}
}