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
	public Acesso BuscaAcesso(String login, String senha) {	
		//Verifica se os parâmetros passados existem no banco de dados
		Acesso acesso = this.acessoRepository.findByLoginAndSenha(login, senha);
		
		//Retorna null caso o objeto não exista na base de dados
		if (acesso == null) {
			return null;
		}else {
			// retorna o obj acesso existente no banco de dados
			return acesso;
		}

	}
	
	//Realiza a persistência do objeto "acesso" no BD
	public void SalvaAcesso(Acesso acesso) {
		this.acessoRepository.save(acesso);
	}
}