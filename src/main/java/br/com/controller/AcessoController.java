package br.com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.model.Acesso;
import br.com.model.Cliente;
import br.com.service.AcessoService;
import br.com.service.ClienteService;

@Controller
public class AcessoController {
	
	@Autowired
	private AcessoService acessoService;
	
	@Autowired	
	private ClienteService clienteService;
	
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
	
	/*
	 * ADICIONAR O HTTPSESSION NO METÓDO DE LOGIN, PARA ARMAZENAR 
	 * O USUÁRIO NA SESSÃO
	 * */
	
	//Realiza o login do usuário
	@PostMapping("/EfetuaLogin")
	public String RealizaLogin(Acesso acesso, RedirectAttributes ra, HttpSession session) {
			
		acesso = this.acessoService.BuscaAcesso(acesso.getLogin(), acesso.getSenha());
		
		if(acesso != null) {
			Cliente cliente = this.clienteService.BuscaClienteAcesso(acesso);
			session.setAttribute("ClienteLogado", cliente);
			return"redirect:/admin/HomePageCliente";
		}else {
			ra.addFlashAttribute("MensagemFlash", "Usuário ou senha inválidos");
			return"redirect:/login";
		}
	}
	
	//Realiza o logout 
	@GetMapping("/sair") 
	public String logout(HttpSession session) {
		session.invalidate();
		return"redirect:/";
	}
	
	//Realiza o bloqueio de acesso
	@GetMapping("/AcessoNegado")
	public String AcessoNegado() {
		return "redirect:/";
	}
	
	/*Recebe o Id do objeto Acesso que está relacionado 
	 * com o cliente e desativa o acesso 
	 */
	@GetMapping("/DesabilitarAcessoCliente")
	public String DesabilitaAcessoCliente(Integer id) {
		this.acessoService.desabilitar(id);
		return"redirect:/sair";
	}
}