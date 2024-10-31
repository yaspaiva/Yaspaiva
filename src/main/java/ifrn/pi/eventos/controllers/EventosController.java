package ifrn.pi.eventos.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.eventos.models.Evento;

@Controller
@RequestMapping("/eventos")
public class EventosController {
@Autowired
	private JpaRepository er;
	
	
	@GetMapping("form")
	public String form(Model model) {
		return "eventos/formEvento"; 
	}
	
	@PostMapping
	public String adicionar(Evento evento) {
		System.out.println(evento);
		er.save(evento);
		
		System.out.println(evento);
		return "eventos/evento-adicionado";
	}
@GetMapping
	public ModelAndView listar() {
		List <Evento> eventos = er.findAll();
		ModelAndView mv = new ModelAndView("eventos/lista");
		mv.addObject("eventos", eventos);
		return mv;
	
	}
}