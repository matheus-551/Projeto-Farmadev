package br.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.Acesso;
import br.com.repository.AcessoRepository;

@Service
public class AcessoService {
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	//Realiza a busca do acesso através do login e senha
	public boolean BuscaAcesso(String login, String senha) {	
		//Verifica se os parâmetros passados existem no banco de dados
		Acesso acesso = this.acessoRepository.findByLoginAndSenha(login, senha);
		
		if(acesso == null) {
			return false;
		}else {
			return true;
		}
	}
	
	//Realiza a persistência do objeto "acesso" no BD
	public void SalvaAcesso(Acesso acesso) {
		this.acessoRepository.save(acesso);
	}
}