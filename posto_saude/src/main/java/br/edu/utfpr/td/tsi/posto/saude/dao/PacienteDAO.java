package br.edu.utfpr.td.tsi.posto.saude.dao;

import java.util.List;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;

public interface PacienteDAO {

	public Long inserir(Paciente paciente);

	public List<Paciente> listarTodos();
	
	public Paciente procurar(Long idPaciente);
}
