package br.com.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.model.Cliente;
import br.com.model.Endereco;
import br.com.service.ClienteService;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	//Exibi a tela de Cadastro de perfil do Clinete
	@GetMapping("/CadastroPerfilCliente") 
	public String ExibirCadastroPerfil() {
		return"Cliente/CadastroPerfilCliente";
	}
	
	//Realiza a chamada do metódo service que faz a persistência no BD
	@PostMapping("/SalvaCliente")
	public String SalvarCliente(@Valid Cliente cliente, BindingResult result,Endereco endereco, HttpSession session, RedirectAttributes ra) {		
		
		if(result.hasErrors()) {
			ra.addFlashAttribute("MensagemFlash", result.getAllErrors().get(0).getDefaultMessage());
			
			return"redirect:/CadastroPerfilCliente";
		}else if(this.clienteService.VerificaEmailExistente(cliente.getEmail()) == true) {
			ra.addFlashAttribute("MensagemFlash", "Já existe um usuário cadastrado com esse email");
			
			return"redirect:/CadastroPerfilCliente";
		}else if(this.clienteService.VerificaCpfExistente(cliente.getCpf()) == true) {
			ra.addFlashAttribute("MensagemFlash", "Já existe um usuário cadastrado com esse CPF");
			
			return"redirect:/CadastroPerfilCliente";
		}else {
			cliente.setEndereco(endereco);
			this.clienteService.SalvaCliente(cliente);
			
			session.invalidate();
			return"redirect:/";
		}
		
	}
	
	//Exibi a tela de Perfil
	@GetMapping("/Client/MeuPerfil")
	public String ExibirPerfilCliente() {
		return"Cliente/MeuPerfilCliente";
	}
	
	//Exibi a tela de alteração de dados
	@GetMapping("/Client/AlterarDadosCliente")
	public String AlteraDadosPerfilCliente(Integer id, Model model) {
		Cliente cliente = this.clienteService.BuscaPorId(id);
		model.addAttribute("cliente", cliente);
		return"Cliente/AlterarDadosCliente";
	}
	
	//Realiza o Update do cliente 
	@PostMapping("/AtualizaCliente")
	public String RealizaAlteracaoCliente(Cliente cliente, Endereco endereco, HttpSession session) {
		cliente.setEndereco(endereco);
		this.clienteService.SalvaCliente(cliente);
		
		session.setAttribute("ClienteLogado", cliente);
		return"redirect:/Client/MeuPerfil";
	}
}
