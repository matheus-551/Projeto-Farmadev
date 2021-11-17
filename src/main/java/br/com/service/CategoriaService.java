package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.Categoria;
import br.com.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//Lista todas as categorias
	public List<Categoria>ListaCategorias(){
		return this.categoriaRepository.findAll();
	}
	
	//Busca a categoria por id
	public Categoria BuscaPorId(Integer id) {
		Categoria categoria = this.categoriaRepository.getById(id);
		return categoria;
	}
	
	//Salva a categoria no BD
	public void SalvaCategoria(Categoria categoria) {
		this.categoriaRepository.save(categoria);
	}
}
