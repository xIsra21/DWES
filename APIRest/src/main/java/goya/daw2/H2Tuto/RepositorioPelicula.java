//package goya.daw2.H2Tuto;
//
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
//@RepositoryRestResource(collectionResourceRel = "peliculas", path = "peliculas")
//public interface RepositorioPelicula extends CrudRepository<Pelicula, Long> {
//}

package goya.daw2.H2Tuto;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositorioPelicula extends JpaRepository<Pelicula, Long> {
}
