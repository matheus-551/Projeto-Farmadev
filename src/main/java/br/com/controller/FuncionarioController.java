package br.com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String SalvaFuncionario(Funcionario funcionario, Endereco endereco) {
		funcionario.setEndereco(endereco);
		this.funcionarioService.SalvaFuncionario(funcionario);
		return"redirect:/loginFuncionario";
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
