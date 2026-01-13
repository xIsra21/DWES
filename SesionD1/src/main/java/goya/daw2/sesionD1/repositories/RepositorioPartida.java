package goya.daw2.sesionD1.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import goya.daw2.sesionD1.entities.Partida;

@Repository
public interface RepositorioPartida extends CrudRepository<Partida, String>{
	public List<Partida> findAllByOrderByPuntuacionDesc();
	
	List<Partida> findByParticipantesNombre(String nombre);
}
