package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
