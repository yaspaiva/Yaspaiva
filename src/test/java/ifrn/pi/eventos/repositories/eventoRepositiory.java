package ifrn.pi.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.eventos.models.Evento;

public interface eventoRepositiory extends JpaRepository<Evento, Long>{
	

}
