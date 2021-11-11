package br.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.Produto;
import br.com.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	//Salva o produto no BD
	public void SalvaProduto(Produto produto) {
		this.produtoRepository.save(produto);
	}
}
