package br.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.model.Cliente;
import br.com.model.Endereco;
import br.com.service.ClienteService;

@Controller
public class MainController {
	

	
	//Exibi a tela principal
	@GetMapping("/")
	public String ExibirIndex() {
		return"index";
	}
	
	//Exibi a tela de usuário logado
	@GetMapping("/admin/HomePageCliente")
	public String exibirHomePageCliente() {
		return"HomePageCliente";
	}
	
	//Exibi tela de recuperação de senha
	@GetMapping("/RecuperarSenha")
	public String ExibirFormRecuperacaoSenha() {
		return "RecuperaSenha";
	}
}
