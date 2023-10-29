package br.edu.utfpr.td.tsi.posto.saude.controle;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.utfpr.td.tsi.posto.saude.dao.EnderecoDAO;
import br.edu.utfpr.td.tsi.posto.saude.dao.PacienteDAO;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;

@Controller
public class PacienteController {

	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Autowired
	private PacienteDAO pacienteDAO;

	@Autowired
	private EnderecoDAO enderecoDAO;

	@GetMapping(value = "/listarPacientes")
	public String listar(Model model) {
		List<Paciente> pacientes = pacienteDAO.listarTodos();
		for (Paciente paciente : pacientes) {
			if (paciente.getData_nascimento() != null) {
				paciente.setData_nascimento_formatada(paciente.getData_nascimento().format(formatter));
			}
		}
		model.addAttribute("pacientes", pacientes);
		return "listarPacientes";
	}

	@GetMapping(value = "/cadastrarPaciente")
	public String cadastrar() {
		return "cadastrarPaciente";
	}

	@PostMapping("/cadastrarPaciente")
	public String cadastrar(@ModelAttribute("paciente") Paciente paciente) {
		Long idPaciente = pacienteDAO.inserir(paciente);
		enderecoDAO.inserir(paciente.getEndereco(), idPaciente);
		return "redirect:/listarPacientes";
	}

}
