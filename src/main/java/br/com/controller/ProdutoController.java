package br.com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.model.Produto;
import br.com.model.TipoMedicamento;
import br.com.service.CategoriaService;
import br.com.service.FornecedorService;
import br.com.service.ProdutoService;

@Controller
public class ProdutoController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@Autowired
	private ProdutoService produtoService;
	
	//Metódo que trás as informações do enum
	@ModelAttribute("ListaTipoMedicamento")
	public TipoMedicamento[] getListaTipoMedicamento() {
		return TipoMedicamento.values();
	}
	
	//Exibi a tela de produto para o funcionario
	@GetMapping("/Admin/ProdutoFuncionario")
	public String ExibirProdutoFuncionario(Model model) {
		model.addAttribute("ListaProduto", this.produtoService.ListaProdutos());
		return"Produtos/ProdutoFuncionario";
	}
	
	//Exibi o form de cadastro de produto
	@GetMapping("/Admin/CadastroProduto")
	public String ExbirCadastroProduto(Model model) {
		Produto produto = new Produto();
		model.addAttribute("ProdutoObj", produto);
		model.addAttribute("ListFornecedor", this.fornecedorService.ListaFornecedores());
		model.addAttribute("ListCategorias", this.categoriaService.ListaCategorias());
		return"Produtos/CadastroProduto";
	}
		
	//Realiza a chamada do metódo do service que salva o produto
	@PostMapping("/SalvaProduto")
	public String SalvarProduto(@Valid Produto produto, BindingResult result, RedirectAttributes ra) {	
		if(result.hasErrors()) {
			ra.addFlashAttribute("MensagemFlash", result.getAllErrors().get(0).getDefaultMessage());
			return"redirect:/Admin/CadastroProduto";
		}else {
			this.produtoService.SalvaProduto(produto);
			return"redirect:/Admin/ProdutoFuncionario";
		}
	}
		
	//Altera o produto
	@GetMapping("/AlteraProduto")
	public String AlteraPrduto(Integer id, Model model) {
		Produto produto = this.produtoService.BuscaPorId(id);
		model.addAttribute("ProdutoObj", produto);
		model.addAttribute("ListFornecedor", this.fornecedorService.ListaFornecedores());
		model.addAttribute("ListCategorias", this.categoriaService.ListaCategorias());
		return"Produtos/CadastroProduto";
	}
}
