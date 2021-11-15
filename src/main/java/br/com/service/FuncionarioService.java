package br.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.Acesso;
import br.com.model.Funcionario;
import br.com.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	//Busca por id
	public Funcionario BuscaPorId(Integer id) {
		Funcionario funcionario = this.funcionarioRepository.getById(id);
		return funcionario;
	}
	
	//Busca contrato existente
	public boolean VerificaIdentificacaoContrato(String IdentificacaoContrato) {
		return this.funcionarioRepository.existsByContrato(IdentificacaoContrato);
	}
	
	//Busca o cpf existente
	public boolean VerificaCpfExistente(String cpf) {
		return this.funcionarioRepository.existsByCpf(cpf);
	}
	
	//Realiza a busca do funcionario através do acesso
	public Funcionario BuscaFuncionarioAcesso(Acesso acesso) {
		Funcionario funcionario = this.funcionarioRepository.findByAcesso(acesso);
		return funcionario;
	}
	
	//Realiza a persistencia do objeto "funcionario" mo BD
	public void SalvaFuncionario(Funcionario funcionario) {
		this.funcionarioRepository.save(funcionario);
	}
}
