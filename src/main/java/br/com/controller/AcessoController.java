package br.com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.model.Acesso;
import br.com.model.Cliente;
import br.com.model.Funcionario;
import br.com.service.AcessoService;
import br.com.service.ClienteService;
import br.com.service.FuncionarioService;

@Controller
public class AcessoController {
	
	@Autowired
	private AcessoService acessoService;
	
	@Autowired	
	private ClienteService clienteService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	//Exibi a tela de login do cliente
	@GetMapping("/loginCliente")
	public String ExibirLoginCliente() {
		return"Cliente/LoginCliente";
	}
	
	//Exibi a tela de login do funcionário
	@GetMapping("/loginFuncionario")
	public String ExibirLoginFuncionario() {
		return"Funcionario/LoginFuncionario";
	}
	
	//Exibi a tela de cadastro de acesso do cliente
	@GetMapping("/CadastroAcessoCliente")
	public String ExibirCadastroAcessoCliente() {
		return"Cliente/CadastroAcessoCliente";
	}
	
	//Exibi a tela de cadastro de acesso do funcionário
	@GetMapping("/CadastroAcessoFuncionario")
	public String ExibirCadastroAcessoFuncionario() {
		return"Funcionario/CadastroAcessoFuncionario";
	}
	
	//Metódo que recebe as informações do form de cadastro de acesso
	@PostMapping("/SalvaFormAcesso")
	public String SalvaFormAcesso(Acesso acesso, RedirectAttributes ra) {
	
		if(acesso.getTipoPerfil() == 1) {
			//Chama o metódo do service que realiza a persistencia
			this.acessoService.SalvaAcesso(acesso);
			
			//Envia o obj de acesso para a próxima pagina
			ra.addFlashAttribute("AcessoObjct", acesso);
			
			return"redirect:/CadastroPerfilCliente";
		}else{
			this.acessoService.SalvaAcesso(acesso);
			
			ra.addFlashAttribute("AcessoObjct", acesso);
			
			return"redirect:/CadastroPerfilFuncionario";
		}
	}
	
	/*
	 * ADICIONAR O HTTPSESSION NO METÓDO DE LOGIN, PARA ARMAZENAR 
	 * O USUÁRIO NA SESSÃO
	 * */
	
	//Realiza o login do usuário cliente
	@PostMapping("/EfetuaLoginCliente")
	public String RealizaLoginCliente(Acesso acesso, RedirectAttributes ra, HttpSession session) {
			
		acesso = this.acessoService.BuscaAcesso(acesso.getLogin(), acesso.getSenha());
		
		if(acesso != null && acesso.getTipoPerfil() == 01 && acesso.getStatus() != false) {
			Cliente cliente = this.clienteService.BuscaClienteAcesso(acesso);
			session.setAttribute("ClienteLogado", cliente);
			
			return"redirect:/Client/HomePageCliente";
		}else if(acesso != null && acesso.getStatus() == false){
			ra.addFlashAttribute("MensagemFlash", "Esta conta foi desativada");
		}else {
			ra.addFlashAttribute("MensagemFlash", "Login ou senha invalido");
		}
		
		return"redirect:/loginCliente";
	}
	
	@PostMapping("/EfetuaLoginFuncionario")
	public String RealizaLoginFuncionario(Acesso acesso, RedirectAttributes ra, HttpSession session) {
		
		acesso = this.acessoService.BuscaAcesso(acesso.getLogin(), acesso.getSenha());
		
		if(acesso != null && acesso.getTipoPerfil() == 02 && acesso.getStatus() != false) {
			Funcionario funcionario = this.funcionarioService.BuscaFuncionarioAcesso(acesso);
			session.setAttribute("FuncionarioLogado", funcionario);
			
			return"redirect:/Admin/HomePageFuncionario";
		}else if(acesso != null && acesso.getStatus() == false) {
			ra.addFlashAttribute("MensagemFlash", "Esta conta foi desativada");
		}else {
			ra.addFlashAttribute("MensagemFlash", "Login ou senha invalido");
		}
		
		return"redirect:/loginFuncionario";
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
	@GetMapping("/DesabilitarAcesso")
	public String DesabilitaAcesso(Integer id) {
		this.acessoService.desabilitar(id);
		return"redirect:/sair";
	}
}