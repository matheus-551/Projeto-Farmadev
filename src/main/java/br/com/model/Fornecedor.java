package br.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fornecedor {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String NomeFornecedor;
	@Column(unique = true ,nullable = false, length = 14)
	private String cnpj;
	
	public Fornecedor() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeFornecedor() {
		return NomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		NomeFornecedor = nomeFornecedor;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "Fornecedor [id=" + id + ", NomeFornecedor=" + NomeFornecedor + ", cnpj=" + cnpj + "]";
	}
}