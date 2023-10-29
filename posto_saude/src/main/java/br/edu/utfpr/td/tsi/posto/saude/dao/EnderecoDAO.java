package br.edu.utfpr.td.tsi.posto.saude.dao;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Endereco;

public interface EnderecoDAO {
	
	public void inserir(Endereco endereco, Long idPaciente);
		
	public Endereco procurar(Long idPaciente);

}
