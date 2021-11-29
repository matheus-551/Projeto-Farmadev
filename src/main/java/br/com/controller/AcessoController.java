package br.com.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.model.Acesso;
import br.com.model.Cliente;
import br.com.model.Funcionario;
import br.com.service.AcessoService;
import br.com.service.ClienteService;
import br.com.service.EmailService;
import br.com.service.FuncionarioService;

@Controller
public class AcessoController {
	
	@Autowired
	private AcessoService acessoService;
	
	@Autowired	
	private ClienteService clienteService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private EmailService emailService;
	
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
	
	//Recebe as informações do form de acesso cliente
	@PostMapping("/SalvaClienteAcesso")
	public String SalvarAcessoCliente(@Valid Acesso acesso, BindingResult result, HttpSession session,RedirectAttributes ra) {
		
		//Retona os erros encotrados pelo bean Validation
		if(result.hasErrors()) {
			ra.addFlashAttribute("MensagemFlash", result.getAllErrors().get(0).getDefaultMessage());
			return"redirect:/CadastroAcessoCliente";
		}else if(this.acessoService.VerificaLoginExitente(acesso.getLogin()) == true && acesso.getTipoPerfil() == 1) {
			ra.addFlashAttribute("MensagemFlash","Login já existente");
			return"redirect:/CadastroAcessoCliente";
		}else{
			//Chama o metódo do service que realiza a persistencia
			this.acessoService.SalvaAcesso(acesso);
			
			//Envia o obj de acesso para a próxima pagina
			session.setAttribute("AcessoObjct", acesso);
			
			return"redirect:/CadastroPerfilCliente";
		}
	}
	
	//Recebe as informações do form de acesso funcionario
	@PostMapping("/SalvaAcessoFuncionario")
	public String SalvarAcessoFuncionario(@Valid Acesso acesso, BindingResult result, HttpSession session, RedirectAttributes ra) {
		
		if(result.hasErrors()) {
			ra.addFlashAttribute("MensagemFlash", result.getAllErrors().get(0).getDefaultMessage());
			return"redirect:/CadastroAcessoFuncionario";
		}else if(this.acessoService.VerificaLoginExitente(acesso.getLogin()) == true && acesso.getTipoPerfil() == 02) {
			ra.addFlashAttribute("MensagemFlash","Login já existente");
			return"redirect:/CadastroAcessoFuncionario";
		}else {
			this.acessoService.SalvaAcesso(acesso);
			
			session.setAttribute("AcessoObjct", acesso);
			
			return"redirect:/CadastroPerfilFuncionario";
		}		
	}
		
	//Realiza o login do usuário cliente
	@PostMapping("/EfetuaLoginCliente")
	public String RealizaLoginCliente(String login,String senha , RedirectAttributes ra, HttpSession session) {
			
		Acesso acesso = this.acessoService.BuscaAcesso(login, senha);
		
		if(acesso != null && acesso.getLogin().equalsIgnoreCase(login) && acesso.getSenha().equals(senha) && acesso.getTipoPerfil() == 01 && acesso.getStatus() != false) {
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
	
	//Efetua login do funcionário
	@PostMapping("/EfetuaLoginFuncionario")
	public String RealizaLoginFuncionario(String login, String senha, RedirectAttributes ra, HttpSession session) {
		
		Acesso acesso = this.acessoService.BuscaAcesso(login, senha); 
		
		if(acesso != null && acesso.getLogin().equalsIgnoreCase(login) && acesso.getSenha().equals(senha) && acesso.getTipoPerfil() == 02 && acesso.getStatus() != false) {
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
	
	@RequestMapping(path="/sendEmail", method = RequestMethod.GET)
	public void AcessEmail(Cliente cliente) {
		this.emailService.sendMail(cliente);
	}
	
	//Exibi a tela de recuperar senha
	@GetMapping("/RecuperaSenha")
	public String ExibirRecuperaSenha() {
		return"RecuperaSenha";
	}
	
	@PostMapping("/EnviaRecuperaSenha")
	public String RecuperaSenha(String login, RedirectAttributes ra) {
		Acesso acesso = this.acessoService.BuscaAcessoLogin(login);
		
		if(acesso != null) {
			ra.addFlashAttribute("MensagemFlash", "O link para recuperar sua conta foi enviado para seu email");
			Cliente cliente = this.clienteService.BuscaClienteAcesso(acesso);
			
			this.AcessEmail(cliente);
			return"redirect:/";
		}else {
			ra.addFlashAttribute("MensagemFlash", "Usuário não encontrado");
			return"redirect:/RecuperaSenha";
		}
	}
	
	//Método que exibi o template de criar nova senha
	@GetMapping("/Alterarsenha/{id}")
	public String AlteraSenha(@PathVariable Integer id, Model model) {
		Acesso acesso = this.acessoService.BuscaPorId(id);
		model.addAttribute("AcessoObj", acesso);
		return"NovaSenha";
	}
	
	@PostMapping("/SalvaAlteracaoSenha")
	public String SalvaAlteracaoSenha(Acesso acesso) {
		this.acessoService.SalvaAcesso(acesso);
		return"redirect:/";
	}
	
	//Desabilita acesso
	@GetMapping("/DesabilitarAcesso")
	public String DesabilitaAcesso(Integer id) {
		this.acessoService.desabilitar(id);
		return"redirect:/sair";
	}
}