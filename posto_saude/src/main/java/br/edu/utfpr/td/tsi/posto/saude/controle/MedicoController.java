package br.edu.utfpr.td.tsi.posto.saude.controle;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.utfpr.td.tsi.posto.saude.dao.MedicoDAO;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Medico;

@Controller
public class MedicoController {

	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Autowired
	private MedicoDAO medicoDAO;

	@GetMapping(value = "/listarMedicos")
	public String listar(Model model) {
		List<Medico> medicos = medicoDAO.listarTodos();

		for (Medico medico : medicos) {
			if (medico.getData_nascimento() != null) {
				medico.setData_nascimento_formatada(medico.getData_nascimento().format(formatter));
			}
		}

		model.addAttribute("medicos", medicos);
		return "listarMedicos";
	}

	@GetMapping(value = "/cadastrarMedico")
	public String cadastrar() {
		return "cadastrarMedico";
	}

	@PostMapping("/cadastrarMedico")
	public String cadastrar(@ModelAttribute("medico") Medico medico) {
		medicoDAO.inserir(medico);
		return "redirect:/listarMedicos";
	}
}
