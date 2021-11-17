package br.com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.model.Cliente;
import br.com.model.FormaPagamento;
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
	
	@ModelAttribute("ListaFormaPagamento")
	public FormaPagamento[] getListaPagamento() {
		return FormaPagamento.values();
	}
	
	@GetMapping("/Admin/VendaFuncionario")
	public String ExibirVendaFuncionario(Model model) {
		model.addAttribute("ListaVendas", this.vendaService.ListaVendas());
		return"Vendas/VendasFuncionarios";
	}
	
	@GetMapping("/Client/FinalizarCompra")
	public String ExibirFinalizarCompra(Integer id, Model model) {
		Produto produto = this.produtoService.BuscaPorId(id);
		model.addAttribute("ProdutoObj", produto);
		return"Cliente/FinalizaCompra";
	}
	
	@PostMapping("/SalvaVenda")
	public String SalvarVenda(@Valid Venda venda, BindingResult result, Produto produto, Cliente cliente, RedirectAttributes ra, Model model) {
		
		//Recebe o cliente e verifica seu id
		cliente = this.clienteService.BuscaPorId(cliente.getId());
		venda.setCliente(cliente);
		
		//Busca o produto e verifica seu id
		produto = this.produtoService.BuscaPorId(produto.getId());	
		venda.setProduto(produto);
		
		//seta o valor do produto como valor total
		venda.setValorTotal(produto.getPreco());
		
		if(result.hasErrors()) {
			ra.addFlashAttribute("MensagemFlash", result.getAllErrors().get(0).getDefaultMessage());
					
			return"redirect:/Client/HomePageCliente";
		}else if(this.vendaService.VerificaValores(venda.getValorPago(), venda.getValorTotal()) != true){
			ra.addFlashAttribute("MensagemFlash", "Ocorreu um problema ao tentar finalizar a compra");
			
			return"redirect:/Client/HomePageCliente";
		}else {
			this.vendaService.SalvaVenda(venda);
			
			return"redirect:/Client/HomePageCliente";
		}
	}
}
