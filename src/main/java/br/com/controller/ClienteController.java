package br.com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.model.Cliente;
import br.com.model.Endereco;
import br.com.service.ClienteService;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	//Exibi a tela de Cadastro de perfil do Clinete
	@GetMapping("/CadastroPerfil")
	public String ExibirCadastroPerfil(Model model) {
		return"CadastroPerfil";
	}
	
	//Realiza a chamada do metódo service que faz a persistência no BD
	@PostMapping("/SalvaCliente")
	public String SalvaCliente(Cliente cliente, Endereco endereco) {
		cliente.setEndereco(endereco);
		this.clienteService.SalvaCliente(cliente);
		return"redirect:/";
	}
	
	//Exibi a tela de Perfil
	@GetMapping("/admin/MeuPerfil")
	public String ExibirPerfil() {
		return"MeuPerfil";
	}
	
	//Exibi a tela de alteração de dados
	@GetMapping("/admin/AlterarDadosCliente")
	public String AlteraDadosPerfil(Integer id, Model model) {
		Cliente cliente = this.clienteService.BuscaPorId(id);
		model.addAttribute("cliente", cliente);
		return"AlterarDadosCliente";
	}
	
	//Realiza o Update do cliente 
	@PostMapping("/AtualizaCliente")
	public String RealizaAlteracaoCliente(Cliente cliente, Endereco endereco, HttpSession session) {
		cliente.setEndereco(endereco);
		this.clienteService.SalvaCliente(cliente);
		
		session.setAttribute("ClienteLogado", cliente);
		return"redirect:/admin/MeuPerfil";
	}
}
