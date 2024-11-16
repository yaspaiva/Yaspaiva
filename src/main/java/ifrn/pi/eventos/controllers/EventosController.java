package ifrn.pi.eventos.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.eventos.models.Convidado;
import ifrn.pi.eventos.models.Evento;
import ifrn.pi.eventos.repositories.ConvidadoRepository;




@Controller
@RequestMapping("/eventos")
public class EventosController {
@Autowired
	private JpaRepository er;
@Autowired
private ConvidadoRepository cr;


	
	
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
@GetMapping("/{id}")
public ModelAndView detalhar(@PathVariable Long id) {
	ModelAndView md = new ModelAndView();
Optional<Evento> opt = er.findById(id);
if(opt.isEmpty()){
	md.setViewName("redirect:/eventos");
	return md;
	
}
md.setViewName("eventos/detalhes");
Evento evento = opt.get();

md.addObject("evento", evento);
List<Convidado> convidados = cr.findByEvento(evento);
md.addObject("convidados", convidados);

return md;

}
@PostMapping("/{idEvento}")
public String salvarConvidado(@PathVariable Long idEvento, Convidado convidado) {
	System.out.println("id do evento" + idEvento);
	System.out.println(convidado);
	
	Optional <Evento> opt = er.findById(idEvento);
	if(opt.isEmpty()) {
		return "redirect:/eventos";
	}
	
	Evento evento = opt.get();
	convidado.setEvento(evento);
	
	cr.save(convidado);
	
		
	
	

	return "redirect:/eventos/{idEvento}";

	
	
	
	
	
}
}