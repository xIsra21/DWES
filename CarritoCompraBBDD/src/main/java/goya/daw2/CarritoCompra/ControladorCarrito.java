package goya.daw2.CarritoCompra;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goya.daw2.CarritoCompra.Carrito;
import goya.daw2.CarritoCompra.RepositorioCarrito;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorCarrito {

    private RepositorioCarrito repositorio;

    public ControladorCarrito(RepositorioCarrito repositorio) {
        this.repositorio = repositorio;
    }

    static final String[] PRODUCTOS = { "Plátano", "Uvas", "Peras", "Manzanas", "Brocoli" };
    static final double[] PRECIOS = { 4.3, 5.3, 3.25, 2.1, 1.5 };

    // Inicializar datos en la BBDD si no existen
    @PostConstruct
    public void init() {
        for (int i = 0; i < PRODUCTOS.length; i++) {
            if (!repositorio.existsById(PRODUCTOS[i])) {
                repositorio.save(new Carrito(PRODUCTOS[i], 50, PRECIOS[i]));
            }
        }
    }

    @GetMapping("/")
    public String muestraCarrito(Model modelo, HttpSession session) {
        modelo.addAttribute("productos", PRODUCTOS);
        modelo.addAttribute("precios", PRECIOS);
        modelo.addAttribute("productosYstock", repositorio.findAll());

        int[] cantidadesSesion = (int[]) session.getAttribute("cantidades");

        double total = 0;

        if (cantidadesSesion != null) {
            String[] productosYcantidades = new String[PRODUCTOS.length];

            for (int i = 0; i < PRODUCTOS.length; i++) {
                productosYcantidades[i] =
                        PRODUCTOS[i] + " : " + cantidadesSesion[i];

                total += cantidadesSesion[i] * PRECIOS[i];
            }

            modelo.addAttribute("productosYcantidades", productosYcantidades);
        }

        modelo.addAttribute("total", total);
        
        System.out.println("STOCK EN BD:");
        repositorio.findAll().forEach(p -> System.out.println(p.getNombre() + " -> " + p.getStock()));

        
        return "carrito";
    }

    @PostMapping("/")
    public String procesaCarrito(Model modelo,
                                 @RequestParam(name = "cantidades") int[] cantidades,
                                 @RequestParam(name = "comprar", required = false) String comprar,
                                 HttpSession session) {

        modelo.addAttribute("productos", PRODUCTOS);
        modelo.addAttribute("precios", PRECIOS);
        modelo.addAttribute("productosYstock", repositorio.findAll());

        int[] cantidadesSesion = (int[]) session.getAttribute("cantidades");

        // Si ya había carrito en la sesión, se suma
        if (cantidadesSesion != null) {
            for (int i = 0; i < PRODUCTOS.length; i++) {

                cantidadesSesion[i] += cantidades[i];

                Carrito productoBD = repositorio.findById(PRODUCTOS[i]).orElse(null);
                int stockBD = (productoBD == null) ? 0 : productoBD.getStock();

                if (cantidadesSesion[i] > stockBD) {
                    cantidadesSesion[i] = stockBD;
                }
            }
        } else {
            cantidadesSesion = cantidades;
        }

        session.setAttribute("cantidades", cantidadesSesion);

        double total = 0;
        String[] productosYcantidades = new String[PRODUCTOS.length];

        for (int i = 0; i < PRODUCTOS.length; i++) {
            productosYcantidades[i] = String.format(
                    "Del producto %s llevas %d unidades que multiplicadas por %.2f dan %.2f",
                    PRODUCTOS[i],
                    cantidadesSesion[i],
                    PRECIOS[i],
                    cantidadesSesion[i] * PRECIOS[i]
            );

            total += cantidadesSesion[i] * PRECIOS[i];
        }

        modelo.addAttribute("productosYcantidades", productosYcantidades);

        // Si pulsa "comprar"
        if (comprar != null) {
            for (int i = 0; i < PRODUCTOS.length; i++) {

                Carrito productoBD = repositorio.findById(PRODUCTOS[i]).orElse(null);

                if (productoBD != null) {
                    int stockActual = productoBD.getStock();
                    int nuevoStock = stockActual - cantidadesSesion[i];
                    if (nuevoStock < 0) nuevoStock = 0;

                    productoBD.setStock(nuevoStock);
                    repositorio.save(productoBD);
                }

                cantidadesSesion[i] = 0;
            }

            session.setAttribute("cantidades", cantidadesSesion);
            modelo.addAttribute("finalCompra", true);
        }

        total = Math.round(total * 100) / 100.0;
        modelo.addAttribute("total", total);

        System.out.println("STOCK EN BD:");
        repositorio.findAll().forEach(p -> System.out.println(p.getNombre() + " -> " + p.getStock()));

        
        return "carrito";
    }
}
