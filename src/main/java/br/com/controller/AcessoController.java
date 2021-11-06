package br.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.model.Acesso;
import br.com.service.AcessoService;

@Controller
public class AcessoController {
	
	@Autowired
	private AcessoService acessoService;
	
	//Exibi a tela de login do usuário
	@GetMapping("/login")
	public String ExibirLogin() {
		return"login";
	}
	
	//Exibi a tela de cadastro de acesso
	@GetMapping("/CadastroAcesso")
	public String ExibirCadastroAcesso() {
		return"CadastroAcesso";
	}
	
	//Metódo que recebe as informações do form de cadastro de acesso
	@PostMapping("/SalvaFormAcesso")
	public String SalvaForm(Acesso acesso, RedirectAttributes ra) {
		//Chama o metódo do service que realiza a persistencia
		this.acessoService.SalvaAcesso(acesso);
		//Exibi no console o obj acesso recebido pelo form
		System.out.println(acesso);
		
		//Envia o obj de acesso para a próxima pagina
		ra.addFlashAttribute("AcessoObjct", acesso);
		
		return"redirect:/CadastroPerfil";
	}
	
	//Realiza o login do usuário
	@PostMapping("/EfetuaLogin")
	public String RealizaLogin(Acesso acesso, RedirectAttributes ra) {
		
		boolean UsuarioExistente = this.acessoService.BuscaAcesso(acesso.getLogin(), acesso.getSenha());
		
		if (UsuarioExistente == true) {
			return"redirect:/HomePageCliente";
		}else {
			ra.addFlashAttribute("MensagemFlash", "Login ou senha inválido");
			return"redirect:/login";
		}
	}
	
}