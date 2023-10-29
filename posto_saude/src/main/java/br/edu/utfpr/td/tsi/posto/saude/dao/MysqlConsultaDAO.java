package br.edu.utfpr.td.tsi.posto.saude.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Consulta;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Medico;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;

@Component
public class MysqlConsultaDAO implements ConsultaDAO{

	@Autowired
    private DataSource dataSource;
	
	@Autowired
	private MysqlPacienteDAO pacienteDAO;
	
	@Autowired
	private MysqlMedicoDAO medicoDAO;
	
	@Override
	public void inserir(Consulta consulta) {
		
		String sql = "INSERT INTO consultas (data_hora, observacao, pacientes_id, medicos_id, situacao) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            
			Timestamp data_hora = null;
			if (consulta.getData_hora() != null) {
			    data_hora = Timestamp.valueOf(consulta.getData_hora());
			}
			preparedStatement.setTimestamp(1, data_hora);

            preparedStatement.setString(2, consulta.getObservacao());
            preparedStatement.setLong(3, consulta.getPaciente().getId());
            preparedStatement.setLong(4, consulta.getMedico().getId());
            preparedStatement.setString(5, "agendada");
            preparedStatement.executeUpdate();

            conn.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
	}

	@Override
	public List<Consulta> listarTodas() {
		List<Consulta> consultas = new ArrayList<>();
		String sql = "SELECT id, data_hora, observacao, medicos_id, pacientes_id, situacao FROM consultas";

		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				Long medicos_id = resultSet.getLong("medicos_id");
				Long pacientes_id = resultSet.getLong("pacientes_id");
				LocalDateTime data_hora = null;
				if (resultSet.getTimestamp("data_hora") != null) {
				    data_hora = resultSet.getTimestamp("data_hora").toLocalDateTime();
				}

				String observacao = resultSet.getString("observacao");
				String situacao = resultSet.getString("situacao");
				
				Paciente paciente = pacienteDAO.procurar(pacientes_id);
				Medico medico = medicoDAO.procurar(medicos_id);
				
				Consulta consulta = new Consulta(id, data_hora, observacao, medico, paciente, situacao);

				consultas.add(consulta);
			}

			preparedStatement.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return consultas;
	}
	@Override
	public boolean procurar(Long idPaciente) {
		String sql = "SELECT id FROM consultas WHERE pacientes_id = ? AND situacao = 'agendada'";
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, idPaciente);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				conn.close();
				preparedStatement.close();
				resultSet.close();
				return true;
			}

			conn.close();
			preparedStatement.close();
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void atualizarSituacao(Long idConsulta, String novaSituacao) {
	    String sql = "UPDATE consultas SET situacao = ? WHERE id = ?";
	    
	    try {
	        Connection conn = dataSource.getConnection();
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        
	        preparedStatement.setString(1, novaSituacao);
	        preparedStatement.setLong(2, idConsulta);
	        
	        preparedStatement.executeUpdate();
	        
	        conn.close();
	        preparedStatement.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
