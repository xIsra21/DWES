package goya.daw2.sesionD1.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import goya.daw2.sesionD1.entities.Participantes;

@Repository
public interface RepositorioParticipantes extends CrudRepository<Participantes, String>{
	public List<Participantes> findAllByOrderByNombreAsc();
}
