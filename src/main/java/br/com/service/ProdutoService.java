package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.Produto;
import br.com.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	//Lista dos os produtos do BD
	public List<Produto>ListaProdutos(){
		return this.produtoRepository.findAll();
	}
	
	public List<Produto>BuscaProdutoNome(String NomeProduto){
		return this.produtoRepository.findProdutoByNomeProduto(NomeProduto);
	}
	
	//Busca Produto por id
	public Produto BuscaPorId(Integer id) {
		Produto produto = this.produtoRepository.getById(id);
		return produto;
	}
	
	//Salva o produto no BD
	public void SalvaProduto(Produto produto) {
		this.produtoRepository.save(produto);
	}
}
