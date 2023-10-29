package br.edu.utfpr.td.tsi.posto.saude.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Medico;

public class MysqlMedicoDAO implements MedicoDAO{
	
	@Autowired
	private DataSource dataSource;

	@Override
	public void inserir(Medico medico) {
		String sql = "INSERT INTO medicos (nome, crm, cpf, data_nascimento, celular, email, sobrenome) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, medico.getNome());
			preparedStatement.setString(2, medico.getCrm());
			preparedStatement.setString(3, medico.getCpf());
			
			Date data = null;
			if (medico.getData_nascimento() != null) {
				data = Date.valueOf(medico.getData_nascimento());
			}
			preparedStatement.setDate(4, data);
			
			preparedStatement.setString(5, medico.getCelular());
			preparedStatement.setString(6, medico.getEmail());
			preparedStatement.setString(7, medico.getSobrenome());

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Inserção falhou, nenhum registro foi adicionado.");
			}

			preparedStatement.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Medico> listarTodos() {
		List<Medico> medicos = new ArrayList<>();
		String sql = "SELECT id, nome, crm, cpf, data_nascimento, celular, email, sobrenome FROM medicos";

		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String nome = resultSet.getString("nome");
				String crm = resultSet.getString("crm");
				String cpf = resultSet.getString("cpf");
				
				LocalDate data_nascimento = null;
				if (resultSet.getDate("data_nascimento") != null) {
					data_nascimento = resultSet.getDate("data_nascimento").toLocalDate();
				}
				
				String celular = resultSet.getString("celular");
				String email = resultSet.getString("email");
				String sobrenome = resultSet.getString("sobrenome");
				
				Medico medico = new Medico(id, nome, sobrenome, crm, cpf, data_nascimento, celular, email);
				
				medicos.add(medico);
			}

			preparedStatement.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return medicos;
	}

	@Override
	public Medico procurar(Long idMedico) {
		String sql = "SELECT nome, sobrenome, crm FROM medicos WHERE id = ?";
		Medico medico = null;
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, idMedico);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String nome = resultSet.getString("nome");
				String sobrenome = resultSet.getString("sobrenome");
				String crm = resultSet.getString("crm");

				medico = new Medico();
				medico.setNome(nome);
				medico.setSobrenome(sobrenome);
				medico.setCrm(crm);
			}

			conn.close();
			preparedStatement.close();
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medico;
	}

}
