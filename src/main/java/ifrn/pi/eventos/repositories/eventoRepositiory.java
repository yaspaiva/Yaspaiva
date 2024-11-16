package ifrn.pi.eventos.repositories;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.eventos.models.Evento;
@Primary
public interface eventoRepositiory extends JpaRepository<Evento, Long>{
	

}