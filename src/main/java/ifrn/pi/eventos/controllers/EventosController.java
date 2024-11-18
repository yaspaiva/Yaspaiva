package ifrn.pi.eventos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/form")
    public String form(Evento evento) {
        return "eventos/formEvento"; 
    }

    @PostMapping
    public String salvar(Evento evento) {
    	System.out.println(evento);
        er.save(evento);
        return "redirect:/eventos";
    }

    @GetMapping
    public ModelAndView listar() {
        List<Evento> eventos = er.findAll();
        ModelAndView mv = new ModelAndView("eventos/lista");
        mv.addObject("eventos", eventos);
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable Long id, Convidado convidado) {
        ModelAndView md = new ModelAndView();
        Optional<Evento> opt = er.findById(id);

        if (opt.isEmpty()) {
            md.setViewName("redirect:/eventos");
            return md;
        }

        Evento evento = opt.get();
        md.setViewName("eventos/detalhes");
        md.addObject("evento", evento);

        List<Convidado> convidados = cr.findByEvento(evento);
        md.addObject("convidados", convidados);

        return md;
    }

    @PostMapping("/{idEvento}")
    public String salvarConvidado(@PathVariable Long idEvento, Convidado convidado) {
        Optional<Evento> opt = er.findById(idEvento);

        if (opt.isEmpty()) {
            return "redirect:/eventos";
        }

        Evento evento = opt.get();
        convidado.setEvento(evento);
        cr.save(convidado);

        return "redirect:/eventos/" + idEvento;
        
    }

    @GetMapping("/{id}/selecionar")
    public ModelAndView selecionarEvento(@PathVariable Long id) {
        ModelAndView md = new ModelAndView();
        Optional<Evento> opt = er.findById(id);

        if (opt.isEmpty()) {
            md.setViewName("redirect:/eventos");
            return md;
        }

        Evento evento = opt.get();
        md.setViewName("eventos/formEvento");
        md.addObject("evento", evento);

        return md;
    }
    
    

    @GetMapping("/{idEvento}/convidados/{idConvidado}/remover")
    public String removerConvidado(@PathVariable Long idEvento, @PathVariable Long idConvidado) {
        Optional<Evento> optEvento = er.findById(idEvento);

        
        if (optEvento.isEmpty()) {
            return "redirect:/eventos";
        }

        
        Optional<Convidado> optConvidado = cr.findById(idConvidado);

        
        if (optConvidado.isPresent()) {
            cr.delete(optConvidado.get());
        }

        
        return "redirect:/eventos/" + idEvento;
    }


    @PostMapping("/{id}/remover")
    public String apagarEvento(@PathVariable Long id) {
        Optional<Evento> opt = er.findById(id);

        if (opt.isPresent()) {
            Evento evento = opt.get();
            List<Convidado> convidados = cr.findByEvento(evento);
            cr.deleteAll(convidados);
            er.delete(evento);
        }

        return "redirect:/eventos";
    }
    
    @GetMapping("/{idEvento}/convidados/{idConvidado}/selecionar")
    public ModelAndView selecionarConvidado(@PathVariable Long idEvento, @PathVariable Long idConvidado) {
        ModelAndView md = new ModelAndView(); 
        Optional<Evento> optEvento = er.findById(idEvento);
        Optional<Convidado> optConvidado = cr.findById(idConvidado);
        
        if(optEvento.isEmpty() || optConvidado.isEmpty()) {
            md.setViewName("redirect:/eventos");
            return md;
        }
        
        Evento evento = optEvento.get();
        Convidado convidado = optConvidado.get();
        
        if(evento.getId() != convidado.getEvento().getId()) {
        	 md.setViewName("redirect:/eventos");
             return md;
        }
        
        md.setViewName("eventos/detalhes");
        md.addObject("convidado", convidado);
        md.addObject("evento", evento);
        md.addObject("convidados" , cr.findByEvento(evento));
       
        return md;
    }

}
