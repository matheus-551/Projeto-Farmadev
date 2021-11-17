package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.Fornecedor;
import br.com.repository.FornecedorRepository;

@Service
public class FornecedorService {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	//Realiza a listagem dos fornecedores
	public List<Fornecedor> ListaFornecedores(){
		return this.fornecedorRepository.findAll();
	}
	
	//Realiza a busca por id 
	public Fornecedor BuscaPorId(Integer id) {
		Fornecedor fornecedor = this.fornecedorRepository.getById(id);
		return fornecedor;
	}
	
	//Verifica se já existe este cnpj no banco
	public boolean VerificaCnpjExistente(String cnpj) {
		return this.fornecedorRepository.existsByCnpj(cnpj);
	}
	
	//Realiza a persistencia do fornecedor no BD
	public void SalvaFornecedor(Fornecedor fornecedor) {
		this.fornecedorRepository.save(fornecedor);
	}
}
