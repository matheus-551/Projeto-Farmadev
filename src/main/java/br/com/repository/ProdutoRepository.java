package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	//Consulta JPQL
	@Query("select p from Produto p where p.NomeProduto like %?1%")
	public List<Produto>findProdutoByNomeProduto(String NomeProduto);

}
