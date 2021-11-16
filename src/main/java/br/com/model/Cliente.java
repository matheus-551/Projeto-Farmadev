package br.com.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Cliente {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	@NotBlank(message = "Preencha o campo de Nome corretamente")
	private String NomeCompleto;
	
	@Column(unique = true, nullable = false, length = 11)
	@CPF(message = "CPF inválido")
	private String cpf;
	
	@Column(nullable = false)
	@Email(message = "Email inválido")
	private String email;
	
	@DateTimeFormat(pattern= "yyyy-MM-dd")
	@Past(message = "Informe uma data de nascimento válida")
	private LocalDate DataNascimento;
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
				+ ", DataNascimento=" + DataNascimento + ", endereco=" + endereco + ", acesso="
				+ acesso + "]";
	}
}
