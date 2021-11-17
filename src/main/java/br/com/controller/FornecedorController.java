package br.com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String SalvarFornecedor(@Valid Fornecedor fornecedor, BindingResult result, RedirectAttributes ra) {
		if(result.hasErrors()) {
			ra.addFlashAttribute("MensagemFlash", result.getAllErrors().get(0).getDefaultMessage());	
			return"redirect:/Admin/CadastroFornecedor";
		}else if(this.fornecedorService.VerificaCnpjExistente(fornecedor.getCnpj()) == true) {
			ra.addFlashAttribute("MensagemFlash", "Já existe um fornecedor cadastrado com esse CNPJ");
			return"redirect:/Admin/CadastroFornecedor";
		}else {
			this.fornecedorService.SalvaFornecedor(fornecedor);
			return"redirect:/Admin/FornecedorFuncionario";
		}
	}
	
	//Realiza a alteração do fornecedor
	@GetMapping("/AlteraFornecedor")
	public String RealizaAlteracaoFornecedor(Integer id, Model model) {
		Fornecedor fornecedor = this.fornecedorService.BuscaPorId(id);
		model.addAttribute("FornecedorObj", fornecedor);
		return"Fornecedor/CadastroFornecedor";
	}
}
