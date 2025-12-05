package goya.daw2.CarritoCompra;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Carrito {

    @Id
    private String nombre;

    private Integer stock;
    private Double precio;

    public Carrito() {}

    public Carrito(String nombre, Integer stock, Double precio) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Carrito [nombre=" + nombre + ", stock=" + stock + ", precio=" + precio + "]";
    }
}
