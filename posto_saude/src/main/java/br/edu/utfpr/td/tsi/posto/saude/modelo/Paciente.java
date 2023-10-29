package br.edu.utfpr.td.tsi.posto.saude.modelo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Paciente {
	private Long id;
	private String nome;
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	private String sobrenome;
	private String celular;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data_nascimento;
	private String cpf;
	private String nome_emergencia;
	private String telefone_emergencia;
	private Endereco endereco;
	private String data_nascimento_formatada;

	public Paciente() {
		super();
	}

	public Paciente(Long id, String nome, String sobrenome, String celular, String email, LocalDate data_nascimento, String cpf,
			String nome_emergencia, String telefone_emergencia, Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.celular = celular;
		this.email = email;
		this.data_nascimento = data_nascimento;
		this.cpf = cpf;
		this.nome_emergencia = nome_emergencia;
		this.telefone_emergencia = telefone_emergencia;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome_emergencia() {
		return nome_emergencia;
	}

	public void setNome_emergencia(String nome_emergencia) {
		this.nome_emergencia = nome_emergencia;
	}

	public String getTelefone_emergencia() {
		return telefone_emergencia;
	}

	public void setTelefone_emergencia(String telefone_emergencia) {
		this.telefone_emergencia = telefone_emergencia;
	}

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getData_nascimento_formatada() {
		return data_nascimento_formatada;
	}

	public void setData_nascimento_formatada(String data_nascimento_formatada) {
		this.data_nascimento_formatada = data_nascimento_formatada;
	}

}
