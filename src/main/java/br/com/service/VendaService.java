package br.com.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.Produto;
import br.com.model.Venda;
import br.com.repository.VendaRepository;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	public boolean VerificaValores(double ValorPago, double ValorTotal) {
		if (ValorPago >= ValorTotal) {
			System.out.println("ok");
			return true;
		}else {
			System.out.println("Fudeu");
			return false;
		}
	}
	
	//Salva venda
	public void SalvaVenda(Venda venda) {

		venda.setDataVenda(LocalDate.now());
		venda.setHoraVenda(LocalTime.now());
		
		Produto produto = venda.getProduto();
		produto.setEstoque(produto.getEstoque() - 1);
		this.produtoService.SalvaProduto(produto);
		
		this.vendaRepository.save(venda);
	}
}
