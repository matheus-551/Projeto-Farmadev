package br.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.model.Cliente;
import br.com.model.Endereco;
import br.com.service.UsuarioService;

@Controller
public class MainController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	//Exibi a tela principal
	@GetMapping("/")
	public String ExibirIndex() {
		return"index";
	}
	
	//Exibi a tela de Cadastro de perfil do Clinete
	@GetMapping("/CadastroPerfil")
	public String ExibirCadastroPerfil() {
		return"CadastroPerfil";
	}
	
	//Realiza a chamada do metódo service que faz a persistência no BD
	@PostMapping("/SalvaCliente")
	public String SalvaCliente(Cliente cliente, Endereco endereco) {
		cliente.setEndereco(endereco);
		this.usuarioService.SalvaCliente(cliente);
		System.out.println(cliente);
		return"redirect:/";
	}

	@GetMapping("/admin/HomePageCliente")
	public String exibiHomePageCliente() {
		return"HomePageCliente";
	}

	//Exibi tela de recuperação de senha
	@GetMapping("/RecuperarSenha")
	public String ExibirFormRecuperacaoSenha() {
		return "RecuperaSenha";
	}
}
