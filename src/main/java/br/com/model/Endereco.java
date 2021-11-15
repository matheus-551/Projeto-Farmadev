package br.com.model;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {
	
	private String cep;
	private String rua;
	private String bairro;
	private Integer numero;	
	private String Complemento;
	
	public Endereco() {
		
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
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
