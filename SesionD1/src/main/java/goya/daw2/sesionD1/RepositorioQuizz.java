package goya.daw2.sesionD1;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface RepositorioQuizz extends CrudRepository<Quizz, Integer>{

}
