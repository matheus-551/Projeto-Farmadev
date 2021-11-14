package br.com.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Endereco {
	
	@NotNull
	@Size(min = 8, max = 8, message = "CEP Inválido")
	private Integer cep;
	
	@NotBlank
	private String rua;
	
	@NotBlank
	private String bairro;
	
	@NotNull
	@Size(max = 5, message = "No maximo 5 digitos")
	private Integer numero;
	
	private String Complemento;
	
	public Endereco() {
		
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return Complemento;
	}

	public void setComplemento(String complemento) {
		Complemento = complemento;
	}

	@Override
	public String toString() {
		return "Endereco [cep=" + cep + ", rua=" + rua + ", bairro=" + bairro + ", numero=" + numero + ", Complemento="
				+ Complemento + "]";
	}
}
