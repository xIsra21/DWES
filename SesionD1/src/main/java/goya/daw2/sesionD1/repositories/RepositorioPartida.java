package goya.daw2.sesionD1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import goya.daw2.sesionD1.entities.Partida;

@Repository
public interface RepositorioPartida extends CrudRepository<Partida, String>{

}
