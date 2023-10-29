package br.edu.utfpr.td.tsi.posto.saude.modelo;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class Consulta {
	private Long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime data_hora;
	private String observacao;
	private Medico medico;
	private Paciente paciente;
	private String situacao;

	public Consulta() {

	}

	public Consulta(Long id, LocalDateTime data_hora, String observacao, Medico medico, Paciente paciente,
			String situacao) {
		super();
		this.id = id;
		this.data_hora = data_hora;
		this.observacao = observacao;
		this.medico = medico;
		this.paciente = paciente;
		this.situacao = situacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getData_hora() {
		return data_hora;
	}

	public void setData_hora(LocalDateTime data_hora) {
		this.data_hora = data_hora;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}
