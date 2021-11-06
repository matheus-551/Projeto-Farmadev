package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{

}
