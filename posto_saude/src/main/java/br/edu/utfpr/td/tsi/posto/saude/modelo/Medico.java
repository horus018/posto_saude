package br.edu.utfpr.td.tsi.posto.saude.modelo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Medico {

	private Long id;
	private String nome;
	private String sobrenome;
	private String crm;
	private String cpf;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data_nascimento;
	private String celular;
	private String email;
	private String data_nascimento_formatada;

	public Medico() {
		super();
	}

	public Medico(Long id, String nome, String sobrenome, String crm, String cpf, LocalDate data_nascimento, String celular,
			String email) {
		super();
		this.id = id;
		this.setNome(nome);
		this.setSobrenome(sobrenome);
		this.crm = crm;
		this.cpf = cpf;
		this.data_nascimento = data_nascimento;
		this.celular = celular;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
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

	public String getData_nascimento_formatada() {
		return data_nascimento_formatada;
	}

	public void setData_nascimento_formatada(String data_nascimento_formatada) {
		this.data_nascimento_formatada = data_nascimento_formatada;
	}

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

}
