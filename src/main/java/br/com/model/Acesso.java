package br.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Acesso {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Login é obrigatório")
	@Column(unique = true, nullable = false)
	private String login;
	
	@NotBlank(message = "Senha é obrigatória")
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private Integer TipoPerfil;
	
	@Column(nullable = false)
	private boolean status;
	
	public Acesso() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getTipoPerfil() {
		return TipoPerfil;
	}

	public void setTipoPerfil(Integer tipoPerfi) {
		TipoPerfil = tipoPerfi;
	}
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Acesso [id=" + id + ", login=" + login + ", senha=" + senha + ", TipoPerfil=" + TipoPerfil + ", status="
				+ status + "]";
	}
}
