package br.edu.utfpr.td.tsi.posto.saude.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Endereco;

@Component
public class MysqlEnderecoDAO implements EnderecoDAO {

    @Autowired
    private DataSource dataSource;
    
    @Override
    public void inserir(Endereco endereco, Long idPaciente) {
        String sql = "INSERT INTO enderecos (logradouro, numero, cep, complemento, bairro, pacientes_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, endereco.getLogradouro());
            preparedStatement.setString(2, endereco.getNumero());
            preparedStatement.setString(3, endereco.getCep());
            preparedStatement.setString(4, endereco.getComplemento());
            preparedStatement.setString(5, endereco.getBairro());
            preparedStatement.setLong(6, idPaciente);
            preparedStatement.executeUpdate();

            conn.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Endereco procurar(Long idPaciente) {
    	 String sql = "SELECT * FROM enderecos WHERE pacientes_id = ?";
    	 Endereco endereco = null;
    	    try {
    	        Connection conn = dataSource.getConnection();
    	        PreparedStatement preparedStatement = conn.prepareStatement(sql);
    	        preparedStatement.setLong(1, idPaciente);
    	        ResultSet resultSet = preparedStatement.executeQuery();

    	        if (resultSet.next()) {
    	            Long id = resultSet.getLong("id");
    	            String logradouro = resultSet.getString("logradouro");
    	            String numero = resultSet.getString("numero");
    	            String cep = resultSet.getString("cep");
    	            String complemento = resultSet.getString("complemento");
    	            String bairro = resultSet.getString("bairro");
    	            
    	            endereco = new Endereco(id, bairro, logradouro, numero, complemento, cep);
    	            
    	        }

    	        conn.close();
    	        preparedStatement.close();
    	        resultSet.close();
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
		return endereco;
    }

}
