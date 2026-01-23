package goya.daw2.H2Tuto;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PeliculaController {

  private final RepositorioPelicula repositorio;

  PeliculaController(RepositorioPelicula repositorio) {
    this.repositorio = repositorio;
  }


  @GetMapping("/peliculas")
  List<Pelicula> all() {
    return repositorio.findAll();
  }

  @PostMapping("/peliculas")
  Pelicula newPelicula(@RequestBody Pelicula newPelicula) {
    return repositorio.save(newPelicula);
  }

  
  @GetMapping("/peliculas/{id}")
  ResponseEntity<?> one(@PathVariable(name="id") Long id) {
    if (repositorio.findById(id).isPresent()) {
		return ResponseEntity.ok(repositorio.findById(id));
	}
    return ResponseEntity.notFound().build();
  }
  
  @PutMapping("/peliculas/{id}")
  ResponseEntity<?> replacePelicula(
          @RequestBody Pelicula newPelicula,
          @PathVariable(name="id") Long id) {

      return repositorio.findById(id)
          .map(pelicula -> {
              pelicula.setNombre(newPelicula.getNombre());
              pelicula.setDirector(newPelicula.getDirector());
              pelicula.setClasificacion(newPelicula.getClasificacion());

              Pelicula actualizada = repositorio.save(pelicula);
              return ResponseEntity.ok(actualizada);
          })
          .orElseGet(() -> {
              Pelicula creada = repositorio.save(newPelicula);
              return ResponseEntity.status(201).body(creada);
          });
  }


  @DeleteMapping("/peliculas/{id}")
  ResponseEntity<?> deletePelicula(@PathVariable(name="id") Long id) {
	  if (repositorio.findById(id).isPresent()) {
		    Pelicula p = repositorio.findById(id).get();
		  	repositorio.deleteById(id);
			return ResponseEntity.ok(p);
		}
	    return ResponseEntity.notFound().build();
    
  }
  
  @PatchMapping("/peliculas/{id}")
  ResponseEntity<?> patchPelicula(
          @RequestBody Pelicula peliculaParcial,
          @PathVariable(name="id") Long id) {

      return repositorio.findById(id)
          .map(pelicula -> {

              if (peliculaParcial.getNombre() != null) {
                  pelicula.setNombre(peliculaParcial.getNombre());
              }

              if (peliculaParcial.getDirector() != null) {
                  pelicula.setDirector(peliculaParcial.getDirector());
              }

              if (peliculaParcial.getClasificacion() != null) {
                  pelicula.setClasificacion(peliculaParcial.getClasificacion());
              }

              Pelicula actualizada = repositorio.save(pelicula);
              return ResponseEntity.ok(actualizada);
          })
          .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
