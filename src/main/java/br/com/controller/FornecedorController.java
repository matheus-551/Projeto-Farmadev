package br.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.model.Fornecedor;
import br.com.service.FornecedorService;

@Controller
public class FornecedorController {
	
	@Autowired
	private FornecedorService fornecedorService;
	
	//Exibi a tela dos fornecedores
	@GetMapping("/Admin/FornecedorFuncionario")
	public String ExibirFornecedorFuncionario(Model model) {
		model.addAttribute("ListaFuncionarios", this.fornecedorService.ListaFornecedores());
		return"Fornecedor/FornecedorFuncionario";
	}
	
	//Exibi a tela de formulário de fornecedor
	@GetMapping("/Admin/CadastroFornecedor")
	public String ExibirCadastroFornecedor(Model model) {
		Fornecedor fornecedor = new Fornecedor();
		model.addAttribute("FornecedorObj", fornecedor);
		return"Fornecedor/CadastroFornecedor";
	}
	
	//Realiza a chamada do metódo do service que realiza a persistência no BD
	@PostMapping("/SalvaFornecedor")
	public String SalvarFornecedor(Fornecedor fornecedor) {
		this.fornecedorService.SalvaFornecedor(fornecedor);
		return"redirect:/Admin/FornecedorFuncionario";
	}
	
	//Realiza a alteração do fornecedor
	@GetMapping("/AlteraFornecedor")
	public String RealizaAlteracaoFornecedor(Integer id, Model model) {
		Fornecedor fornecedor = this.fornecedorService.BuscaPorId(id);
		model.addAttribute("FornecedorObj", fornecedor);
		return"Fornecedor/CadastroFornecedor";
	}
	
	@GetMapping("/DeletaFuncionario")
	public String RealizaDelecaoFornecedor(Integer id) {
		this.fornecedorService.DeletaFornecedor(id);
		return"redirect:/Admin/FornecedorFuncionario";
	}
}
