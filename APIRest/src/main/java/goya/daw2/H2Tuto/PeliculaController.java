package goya.daw2.H2Tuto;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
  Pelicula replacePelicula(@RequestBody Pelicula newPelicula, @PathVariable Long id) {
    
    return repositorio.findById(id)
      .map(pelicula -> {
        pelicula.setNombre(newPelicula.getNombre());
        pelicula.setDirector(newPelicula.getDirector());
        pelicula.setClasificacion(newPelicula.getClasificacion());
        return repositorio.save(pelicula);
      })
      .orElseGet(() -> {
        return repositorio.save(newPelicula);
      });
  }

  @DeleteMapping("/peliculas/{id}")
  void deletePelicula(@PathVariable(name="id") Long id) {
    repositorio.deleteById(id);
  }
}
