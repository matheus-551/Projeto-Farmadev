package br.com.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Cliente {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String NomeCompleto;
	@Column(unique = true, nullable = false)
	private String cpf;
	@Column(nullable = false)
	private String email;
	@DateTimeFormat(pattern= "yyyy-MM-dd")
	private LocalDate DataNascimento;
	@Column(nullable = false)
	private boolean status;
	private Endereco endereco;
	@OneToOne
	private Acesso acesso;
	
	public Cliente() {
		
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

	public void setNomeCompleto(String NomeCompleto) {
		this.NomeCompleto = NomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return DataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		DataNascimento = dataNascimento;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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
		return "Cliente [id=" + id + ", NomeCompleto=" + NomeCompleto + ", cpf=" + cpf + ", email=" + email
				+ ", DataNascimento=" + DataNascimento + ", status=" + status + ", endereco=" + endereco + ", acesso="
				+ acesso + "]";
	}
}
