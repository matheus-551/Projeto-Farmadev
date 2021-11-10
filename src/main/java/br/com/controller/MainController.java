package br.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.service.CategoriaService;

@Controller
public class MainController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	//Exibi a tela principal
	@GetMapping("/")
	public String ExibirIndex() {
		return"index";
	}
	
	//Exibi a tela de Cliente Logado
	@GetMapping("/Client/HomePageCliente")
	public String ExibirHomePageCliente() {
		return"Cliente/HomePageCliente";
	}
	
	//Exibi a tela de Fucionário Logado
	@GetMapping("/Admin/HomePageFuncionario")
	public String ExibirHomePageFuncionario(Model model) {
		model.addAttribute("ListaCategorias", this.categoriaService.ListaCategorias());
		return"Funcionario/HomePageFuncionario";
	}
}
