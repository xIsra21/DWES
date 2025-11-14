package goya.daw2.CarritoCompra;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorCarrito {
	
	static RepositorioStock stock=new RepositorioStock();	
	public static void addStock() {
		stock.anadir("Manzanas", 5);
		stock.anadir("Peras", 5);
		stock.anadir("Melones", 5);
		stock.guardar();
	}
	@GetMapping("/")
	public String principal(Model modelo) {
		addStock();
		System.out.println(stock.getAll());
		modelo.addAttribute("stock", stock.getAll());
		
		return "index";
	}
	
	
	
}
