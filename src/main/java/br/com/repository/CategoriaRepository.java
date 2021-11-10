package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
