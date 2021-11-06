package br.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Produto {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String NomeProduto;
	@Column(nullable = false, length = 6000)
	private String descricao;
	@Column(nullable = false)
	private String TipoMedicamento;
	@Column(nullable = false)
	private String TipoTarja;
	@Column(nullable = false)
	private double preco;
	@Column(nullable = false)
	private Integer estoque;
	private String lote;
	private boolean status;
	@ManyToOne
	private Fornecedor fornecedor;
	
	public Produto() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return NomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		NomeProduto = nomeProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipoMedicamento() {
		return TipoMedicamento;
	}

	public void setTipoMedicamento(String tipoMedicamento) {
		TipoMedicamento = tipoMedicamento;
	}

	public String getTipoTarja() {
		return TipoTarja;
	}

	public void setTipoTarja(String tipoTarja) {
		TipoTarja = tipoTarja;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", NomeProduto=" + NomeProduto + ", descricao=" + descricao + ", TipoMedicamento="
				+ TipoMedicamento + ", TipoTarja=" + TipoTarja + ", preco=" + preco + ", estoque=" + estoque + ", lote="
				+ lote + ", status=" + status + ", fornecedor=" + fornecedor + "]";
	}
}