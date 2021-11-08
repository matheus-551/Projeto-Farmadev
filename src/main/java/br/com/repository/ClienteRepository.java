package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.Acesso;
import br.com.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	//SELECT * FROM Cliente WHERE ACESSO_ID = ?
	public Cliente findByAcesso(Acesso acesso);
}
