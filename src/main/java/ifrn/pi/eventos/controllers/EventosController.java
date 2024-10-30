package ifrn.pi.eventos.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pi.eventos.models.Evento;

@Controller
public class EventosController {
@Autowired
	private JpaRepository er;
	
	
	@RequestMapping("/eventos/form")
	public String form(Model model) {
		return "formEvento"; 
	}
	
	@PostMapping("/eventos")
	public String adicionar(Evento evento) {
		System.out.println(evento);
		er.save(evento);
		
		System.out.println(evento);
		return "evento-adicionado";
	}

	
}