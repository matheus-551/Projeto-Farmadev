package br.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.Cliente;
import br.com.repository.ClienteRepository;

@Service
public class UsuarioService {
		
	@Autowired
	private ClienteRepository clienteRepository;
	
	//Realiza a persistencia do objeto "cliente" mo BD
	public void SalvaCliente(Cliente cliente) {
		this.clienteRepository.save(cliente);
	}
}
