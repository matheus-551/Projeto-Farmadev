package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Integer>{

}
