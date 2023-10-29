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
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Endereco;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;

@Component
public class MysqlPacienteDAO implements PacienteDAO {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private MysqlEnderecoDAO enderecoDAO;

	@Override
	public Long inserir(Paciente paciente) {
		Long idPaciente = null;
		String sql = "INSERT INTO pacientes (nome, celular, email, data_nascimento, cpf, nome_emergencia, telefone_emergencia, sobrenome) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, paciente.getNome());
			preparedStatement.setString(2, paciente.getCelular());
			preparedStatement.setString(3, paciente.getEmail());

			Date data = null;
			if (paciente.getData_nascimento() != null) {
				data = Date.valueOf(paciente.getData_nascimento());
			}
			preparedStatement.setDate(4, data);

			preparedStatement.setString(5, paciente.getCpf());
			preparedStatement.setString(6, paciente.getNome_emergencia());
			preparedStatement.setString(7, paciente.getTelefone_emergencia());
			preparedStatement.setString(8, paciente.getSobrenome());

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Inserção falhou, nenhum registro foi adicionado.");
			}

			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					idPaciente = generatedKeys.getLong(1);
				} else {
					throw new SQLException("Inserção falhou, não foi possível obter o ID gerado.");
				}
			}

			preparedStatement.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return idPaciente;
	}

	@Override
	public List<Paciente> listarTodos() {
		List<Paciente> pacientes = new ArrayList<>();
		String sql = "SELECT id, nome, celular, email, data_nascimento, cpf, nome_emergencia, telefone_emergencia, sobrenome FROM pacientes";

		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String nome = resultSet.getString("nome");
				String celular = resultSet.getString("celular");
				String email = resultSet.getString("email");

				LocalDate data_nascimento = null;
				if (resultSet.getDate("data_nascimento") != null) {
					data_nascimento = resultSet.getDate("data_nascimento").toLocalDate();
				}

				String cpf = resultSet.getString("cpf");
				String nome_emergencia = resultSet.getString("nome_emergencia");
				String telefone_emergencia = resultSet.getString("telefone_emergencia");
				String sobrenome = resultSet.getString("sobrenome");
				
				Endereco endereco = enderecoDAO.procurar(id);
				Paciente paciente = new Paciente(id, nome, sobrenome, celular, email, data_nascimento, cpf,
						nome_emergencia, telefone_emergencia, endereco);

				pacientes.add(paciente);
			}

			preparedStatement.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pacientes;
	}

	@Override
	public Paciente procurar(Long idPaciente) {
		String sql = "SELECT nome, sobrenome, cpf FROM pacientes WHERE id = ?";
		Paciente paciente = null;
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, idPaciente);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String nome = resultSet.getString("nome");
				String sobrenome = resultSet.getString("sobrenome");
				String cpf = resultSet.getString("cpf");

				paciente = new Paciente();
				paciente.setNome(nome);
				paciente.setSobrenome(sobrenome);
				paciente.setCpf(cpf);
			}

			conn.close();
			preparedStatement.close();
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paciente;
	}
}
