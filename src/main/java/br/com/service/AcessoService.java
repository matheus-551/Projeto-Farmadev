package br.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.Acesso;
import br.com.repository.AcessoRepository;

@Service
public class AcessoService {
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	//Realiza a busca do acesso através do id
	public Acesso BuscaPorId(Integer id) {
		Acesso acesso = this.acessoRepository.getById(id);
		return acesso;
	}
	
	//Realiza a busca do acesso através do login e senha
	public Acesso BuscaAcesso(String login, String senha) {	
		//Verifica se os parâmetros passados existem no banco de dados
		Acesso acesso = this.acessoRepository.findByLoginAndSenha(login, senha);
		
		
		if(acesso.getLogin().equalsIgnoreCase(login) && acesso.getSenha().equals(senha)) {
			// retorna o obj acesso existente no banco de dados
			return acesso;
		}else if(acesso.getStatus() == false) {
			return acesso;
		}else {
			return null;
		}
	}
	
	//Realiza a persistência do objeto "acesso" no BD
	public void SalvaAcesso(Acesso acesso) {
		this.acessoRepository.save(acesso);
	}
	
	//Desabilia o acesso do Cliente
	public void desabilitar(Integer id) {
		Acesso acesso = BuscaPorId(id);
		acesso.setStatus(false);
		SalvaAcesso(acesso);
	}
}