package br.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
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
	public String ExibirHomePageFuncionario() {
		return"Funcionario/HomePageFuncionario";
	}
}
