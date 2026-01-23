package goya.daw2.H2Tuto;

class PeliculaNotFoundException extends RuntimeException {
	PeliculaNotFoundException(Long id) {
		super("Could not find pelicula " + id);
	}
}

