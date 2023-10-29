package br.edu.utfpr.td.tsi.posto.saude.dao;

import java.util.List;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Medico;

public interface MedicoDAO {
	public void inserir(Medico medico);

	public List<Medico> listarTodos();
	
	public Medico procurar(Long idMedico);
}
