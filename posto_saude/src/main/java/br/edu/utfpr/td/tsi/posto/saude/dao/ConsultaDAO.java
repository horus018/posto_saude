package br.edu.utfpr.td.tsi.posto.saude.dao;

import java.util.List;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Consulta;

public interface ConsultaDAO {
	
	public void inserir(Consulta consulta);

	public List<Consulta> listarTodas();
	
	public boolean procurar(Long idPaciente);
	
	public void atualizarSituacao(Long idConsulta, String novaSituacao);

}
