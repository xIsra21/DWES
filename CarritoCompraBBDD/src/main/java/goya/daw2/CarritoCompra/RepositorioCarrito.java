package goya.daw2.CarritoCompra;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCarrito extends CrudRepository<Carrito, String> {

    Carrito findByNombre(String nombre);

    @Query("SELECT c.stock FROM Carrito c WHERE c.nombre = :nombre")
    Integer getStock(String nombre);
}
