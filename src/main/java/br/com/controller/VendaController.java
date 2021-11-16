package br.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.model.Cliente;
import br.com.model.Produto;
import br.com.model.Venda;
import br.com.service.ClienteService;
import br.com.service.ProdutoService;
import br.com.service.VendaService;

@Controller
public class VendaController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private VendaService vendaService;
	
	@GetMapping("/Client/FinalizarCompra")
	public String ExibirFinalizarCompra(Integer id, Model model) {
		Produto produto = this.produtoService.BuscaPorId(id);
		model.addAttribute("ProdutoObj", produto);
		return"Cliente/FinalizaCompra";
	}
	
	@PostMapping("/SalvaVenda")
	public String SalvarVenda(Venda venda, Produto produto, Cliente cliente, RedirectAttributes ra) {
				
		//Recebe o cliente e verifica seu id
		cliente = this.clienteService.BuscaPorId(cliente.getId());
		venda.setCliente(cliente);
		
		//Busca o produto e verifica seu id
		produto = this.produtoService.BuscaPorId(produto.getId());	
		venda.setProduto(produto);
		
		venda.setValorTotal(venda.getValorTotal() + produto.getPreco());
		
		if(venda.getValorPago() < venda.getValorTotal()) {
			ra.addFlashAttribute("MensagemFlash", "Valor pago é inferior ao total");
			
			return"/Client/FinalizarCompra";
		}else if(produto.getEstoque() <= 0){
			ra.addFlashAttribute("MensagemFlash", "Produto indisponível");
			
			return"/Client/FinalizarCompra";
		}else {
			this.vendaService.SalvaVenda(venda);
			return"redirect:/Client/HomePageCliente";
		}
	}
}
