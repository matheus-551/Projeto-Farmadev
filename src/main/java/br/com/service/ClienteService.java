package br.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.Acesso;
import br.com.model.Cliente;
import br.com.repository.ClienteRepository;

@Service
public class ClienteService {
		
	@Autowired
	private ClienteRepository clienteRepository;
	
	//Busca o cliente pelo id
	public Cliente BuscaPorId(Integer id) {
		Cliente cliente = this.clienteRepository.getById(id);
		return cliente;
	}
	
	public boolean VerificaCpfExistente(String cpf) {
		return this.clienteRepository.existsByCpf(cpf);
	}
	
	public boolean VerificaEmailExistente(String email) {
		return this.clienteRepository.existsByEmail(email);
	}
	
	//Busca um cliente pelo acesso_id
	public Cliente BuscaClienteAcesso(Acesso acesso) {
		Cliente cliente = this.clienteRepository.findByAcesso(acesso);
		return cliente;
	}

	//Realiza a persistencia do objeto "cliente" mo BD
	public void SalvaCliente(Cliente cliente) {
		this.clienteRepository.save(cliente);
	}
}
