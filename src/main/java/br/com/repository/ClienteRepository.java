package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.Acesso;
import br.com.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	public Cliente findByAcesso(Acesso acesso);
	
	public boolean existsByCpf(String cpf);
	
	public boolean existsByEmail(String email);
}
