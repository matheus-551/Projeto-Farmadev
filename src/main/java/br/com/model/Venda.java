package br.com.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import jdk.jfr.Timestamp;

@Entity
public class Venda {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@NotNull
	private Cliente cliente;
	
	@ManyToOne
	@NotNull
	private Produto produto;
	
	@Enumerated(EnumType.STRING )
	@NotNull(message = "O campo forma de pagamento deve ser preenchido")
	private FormaPagamento formaPagamento;
	
	@Column(nullable = false)
	@NotNull(message = "Preencha o campo de valor total")
	private double ValorTotal;
	
	@Column(nullable = false)
	@NotNull(message = "preencha o campo de valor a ser pago")
	private double ValorPago;
	
	@Timestamp
	private LocalDate DataVenda;
	@Timestamp
	private LocalTime HoraVenda;
	
	public Venda() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public double getValorTotal() {
		return ValorTotal;
	}

	public void setValorTotal(double valorTotal) {
		ValorTotal = valorTotal;
	}

	public double getValorPago() {
		return ValorPago;
	}

	public void setValorPago(double valorPago) {
		ValorPago = valorPago;
	}

	public LocalDate getDataVenda() {
		return DataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		DataVenda = dataVenda;
	}

	public LocalTime getHoraVenda() {
		return HoraVenda;
	}

	public void setHoraVenda(LocalTime horaVenda) {
		HoraVenda = horaVenda;
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", cliente=" + cliente + ", formaPagamento=" + formaPagamento + ", ValorTotal="
				+ ValorTotal + ", ValorPago=" + ValorPago + "]";
	}
}