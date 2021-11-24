 package br.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.model.Produto;
import br.com.service.CategoriaService;
import br.com.service.ProdutoService;

@Controller
public class MainController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ProdutoService produtoService;
	
	//Exibi a tela principal
	@GetMapping("/")
	public String ExibirIndex(Model model) {
		model.addAttribute("ProdutoObj", this.produtoService.ListaQuatroProdutos());
		return"index";
	}
	
	//Exibi a tela de Cliente Logado
	@GetMapping("/Client/HomePageCliente")
	public String ExibirHomePageCliente(Model model) {
		model.addAttribute("Produtos", this.produtoService.ListaProdutos());
		return"Cliente/HomePageCliente";
	}
	
	//Exibi a tela de Fucionário Logado
	@GetMapping("/Admin/HomePageFuncionario")
	public String ExibirHomePageFuncionario(Model model) {
		model.addAttribute("ListaCategorias", this.categoriaService.ListaCategorias());
		return"Funcionario/HomePageFuncionario";
	}
	
	//Realiza a busca especifica
	@PostMapping("**/PesquisaResultado")
	public ModelAndView pesquisar(@RequestParam("nomePesquisa") String nomePesquisa) {
		ModelAndView mv = new ModelAndView("PesquisaResultado");
		
		List<Produto> ListaProduto;
		
		if(this.produtoService.BuscaProdutoNome(nomePesquisa) == null) {
			ListaProduto = this.produtoService.ListaProdutos();
			mv.addObject("Produtos", ListaProduto);
		}else {
			ListaProduto = this.produtoService.BuscaProdutoNome(nomePesquisa);
			mv.addObject("Produtos", ListaProduto);
		}
		
		return mv;
	}
	
	@GetMapping("/Sobre")
	public String ExibirSobre() {
		return"sobre";
	}
}
