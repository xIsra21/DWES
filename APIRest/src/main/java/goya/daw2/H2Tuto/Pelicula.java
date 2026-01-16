package goya.daw2.H2Tuto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String director;
    private String clasificacion;

    public Pelicula() {}

    public Pelicula(String nombre, String director, String clasificacion) {
        this.nombre = nombre;
        this.director = director;
        this.clasificacion = clasificacion;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDirector() {
        return director;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
}
