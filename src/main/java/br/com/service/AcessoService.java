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
	
	//Verifica se o login digitado já existe
	public boolean VerificaLoginExitente(String Login) {
		return this.acessoRepository.existsByLogin(Login);
	}
	
	//Realiza a busca do acesso através do login e senha
	public Acesso BuscaAcesso(String login, String senha) {	
		//Verifica se os parâmetros passados existem no banco de dados
		Acesso acesso = this.acessoRepository.findByLoginAndSenha(login, senha);
		
		if(acesso != null) {
			return acesso; 
		}else {
			return null;
		}
	}
	
	public Acesso BuscaAcessoLogin(String login) {
		Acesso acesso = this.acessoRepository.findByLogin(login);
		
		if(acesso != null) {
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