package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.Acesso;

public interface AcessoRepository extends JpaRepository<Acesso, Integer>{
	
	public Acesso findByLoginAndSenha(String login, String senha);
	
	public boolean existsByLogin(String login);
}
