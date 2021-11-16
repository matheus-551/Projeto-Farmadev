package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer>{
	
	public boolean existsByCnpj(String cnpj);
	
}
