package br.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Produto {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	@NotBlank(message = "O preenchimento de todos os campos é obrigatório")
	private String NomeProduto;
	
	@Column(nullable = false, length = 6000)
	@NotBlank(message = "O preenchimento de todos os campos é obrigatório")
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private TipoMedicamento tipoMedicamento;
	
	@Column(nullable = false)
	@NotBlank(message = "O preenchimento de todos os campos é obrigatório")
	private String TipoTarja;
	
	@Column(nullable = false)
	@NotNull(message = "Informe um valor válido")
	private double preco;
	
	@Column(nullable = false)
	@NotNull(message = "Informe um valor válido")
	private Integer estoque;
	
	@NotBlank(message = "O preenchimento de todos os campos é obrigatório")
	private String lote;
	
	@Column(nullable = false)
	private boolean status;
	@ManyToOne
	private Categoria categoria;
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

	public TipoMedicamento getTipoMedicamento() {
		return tipoMedicamento;
	}

	public void setTipoMedicamento(TipoMedicamento tipoMedicamento) {
		this.tipoMedicamento = tipoMedicamento;
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
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
				+ tipoMedicamento + ", TipoTarja=" + TipoTarja + ", preco=" + preco + ", estoque=" + estoque + ", lote="
				+ lote + ", status=" + status + ", categoria=" + categoria + ", fornecedor=" + fornecedor + "]";
	}
}