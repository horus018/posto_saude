package br.edu.utfpr.td.tsi.posto.saude.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.utfpr.td.tsi.posto.saude.dao.ConsultaDAO;
import br.edu.utfpr.td.tsi.posto.saude.dao.MedicoDAO;
import br.edu.utfpr.td.tsi.posto.saude.dao.PacienteDAO;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Consulta;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Medico;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;

@Controller
public class ConsultaController {

	@Autowired
	private ConsultaDAO consultaDAO;

	@Autowired
	private MedicoDAO medicoDAO;

	@Autowired
	private PacienteDAO pacienteDAO;

	@GetMapping(value = "/listarConsultas")
	public String listar(Model model) {
		List<Consulta> consultas = consultaDAO.listarTodas();
		model.addAttribute("consultas", consultas);
		return "listarConsultas";
	}

	@GetMapping(value = "/cadastrarConsulta")
	public String cadastrar(Model model) {
		List<Medico> medicos = medicoDAO.listarTodos();
		model.addAttribute("medicos", medicos);

		List<Paciente> pacientes = pacienteDAO.listarTodos();
		model.addAttribute("pacientes", pacientes);
		return "cadastrarConsulta";
	}

	@PostMapping("/cadastrarConsulta")
	public String cadastrar(@ModelAttribute("consulta") Consulta consulta, Model model) {
	    List<Medico> medicos = medicoDAO.listarTodos();
	    List<Paciente> pacientes = pacienteDAO.listarTodos();
	    model.addAttribute("medicos", medicos);
	    model.addAttribute("pacientes", pacientes);

	    if (consultaDAO.procurar(consulta.getPaciente().getId())) {
	        model.addAttribute("erro", "Já existe uma consulta agendada para esse paciente!");
	    } else if (consulta.getMedico().getId() == 0) {
	        model.addAttribute("erro", "Selecione um médico!");
	    } else if (consulta.getPaciente().getId() == 0) {
	        model.addAttribute("erro", "Selecione um paciente!");
	    } else {
	        consultaDAO.inserir(consulta);
	        return "redirect:/listarConsultas";
	    }
	    return "cadastrarConsulta";
	}

	@PostMapping("/atualizarSituacao/{id}")
	public String atualizarSituacao(@PathVariable("id") Long id, @RequestParam("situacao") String situacao, Model model) {
		
		if ("agendada".equals(situacao)) {
			model.addAttribute("erro", "Você não pode voltar uma situação para agendada, cadastre uma nova!");
			List<Consulta> consultas = consultaDAO.listarTodas();
			model.addAttribute("consultas", consultas);
			return "listarConsultas";
		}
		
		consultaDAO.atualizarSituacao(id, situacao);
		return "redirect:/listarConsultas";
	}
	
}
