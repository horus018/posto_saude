package br.edu.utfpr.td.tsi.posto.saude.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping(value = "/")
	public String index() {		
		return "index";
	}
}


