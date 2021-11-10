package br.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String SalvarCategoria(Categoria categoria) {
		this.categoriaService.SalvaCategoria(categoria);
		return"redirect:/Admin/HomePageFuncionario";
	}
	
	//Realiza a alteração da categoria
	@GetMapping("/AlteraCategoria")
	public String RealizaAlteracaoCategoria(Integer id, Model model) {
		Categoria categoria = this.categoriaService.BuscaPorId(id);
		model.addAttribute("CategoriaObj", categoria);
		return"Funcionario/CadastroCategoria";
	}
	
	//Realiza a deleção da categoria
	@GetMapping("/DeletaCategoria")
	public String RealizaDelecaoCategoria(Integer id) {
		this.categoriaService.DeletaCategoria(id);
		return"redirect:/Admin/HomePageFuncionario";
	}
}
