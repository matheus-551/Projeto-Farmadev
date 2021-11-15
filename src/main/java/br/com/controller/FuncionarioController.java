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

import br.com.model.Endereco;
import br.com.model.Funcionario;
import br.com.service.FuncionarioService;

@Controller
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping("/CadastroPerfilFuncionario")
	public String CadastroPerfilFuncionario() {
		return"Funcionario/CadastroPerfilFuncionario";
	}
	
	//Realiza a chamada do metódo do service que faz a persistência no BD
	@PostMapping("/SalvaFuncionario")
	public String SalvaFuncionario(@Valid Funcionario funcionario, BindingResult result, Endereco endereco, HttpSession session, RedirectAttributes ra) {
		
		if(result.hasErrors()) {
			ra.addFlashAttribute("MensagemFlash", result.getAllErrors().get(0).getDefaultMessage());
			
			return"redirect:/CadastroPerfilFuncionario";
		}else if(this.funcionarioService.VerificaCpfExistente(funcionario.getCpf()) == true) {
			ra.addFlashAttribute("MensagemFlash", "Já existe um usuário cadastrado com esse CPF");
			
			return"redirect:/CadastroPerfilFuncionario";
		}else if(this.funcionarioService.VerificaIdentificacaoContrato(funcionario.getContrato()) == true) {
			ra.addFlashAttribute("MensagemFlash", "Contrato já cadastrado");
			
			return"redirect:/CadastroPerfilFuncionario";
		}else {
			funcionario.setEndereco(endereco);
			this.funcionarioService.SalvaFuncionario(funcionario);
			
			session.invalidate();
			return"redirect:/loginFuncionario";
		}
	
	}
	
	//Exibi a tela de Perfil do Funcionario
	@GetMapping("/Admin/MeuPerfil")
	public String ExibirPerfilFuncionario() {
		return"Funcionario/MeuPerfilFuncionario";
	}
	
	@GetMapping("/Admin/AlteraDados")
	public String AlteraDadosPerfilFuncionario(Integer id, Model model) {
		Funcionario funcionario = this.funcionarioService.BuscaPorId(id);
		model.addAttribute("funcionario", funcionario);
		return"Funcionario/AlteraDadosFuncionario";
	}
	
	@PostMapping("/AtualizaFuncionario")
	public String RealizaAlteracaoFuncionario(Funcionario funcionario, Endereco endereco, HttpSession session) {
		funcionario.setEndereco(endereco);
		this.funcionarioService.SalvaFuncionario(funcionario);
		
		session.setAttribute("FuncionarioLogado", funcionario);
		return"redirect:/Admin/MeuPerfil";
	}
}
