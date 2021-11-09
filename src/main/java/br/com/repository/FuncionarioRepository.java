package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.Acesso;
import br.com.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{
	
	//SELECT * FROM FUNCIONARIO WHERE ACESSO_ID = ?
	public Funcionario findByAcesso (Acesso acesso);
}
