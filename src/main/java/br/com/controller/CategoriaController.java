package br.com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.model.Categoria;
import br.com.service.CategoriaService;

@Controller
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	//Acesso ao formulario de cadastro de categoria
	@GetMapping("/Admin/CadastroCategoria")
	public String ExibirCadastroCategoria(Model model) {
		Categoria categoria = new Categoria();
		model.addAttribute("CategoriaObj", categoria);
		return"Funcionario/CadastroCategoria";
	}
	
	//Realiza a chamada do metódo do service para salvar o obj no BD
	@PostMapping("/SalvaCategoria")
	public String SalvarCategoria(@Valid Categoria categoria, BindingResult result, RedirectAttributes ra) {
		
		if(result.hasErrors()) {
			ra.addFlashAttribute("MensagemFlash", result.getAllErrors().get(0).getDefaultMessage());		
			return"redirect:/Admin/CadastroCategoria";
		}else {
			this.categoriaService.SalvaCategoria(categoria);
			return"redirect:/Admin/HomePageFuncionario";
		}
	}
	
	//Realiza a alteração da categoria
	@GetMapping("/AlteraCategoria")
	public String RealizaAlteracaoCategoria(Integer id, Model model) {
		Categoria categoria = this.categoriaService.BuscaPorId(id);
		model.addAttribute("CategoriaObj", categoria);
		return"Funcionario/CadastroCategoria";
	}
}
