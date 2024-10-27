package ifrn.pi.eventos.controllers;


import ifrn.pi.eventos.models.Evento;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventosController {

	@RequestMapping("/eventos/form")
	public String form(Model model) {
		return "formEvento";
	}
	
	@PostMapping("/eventos")
	public String adicionar(Evento evento) {
		
		System.out.println(evento);
		return "evento-adicionado";
	}

	
}