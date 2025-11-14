package goya.daw2.CarritoCompra;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	@PostMapping("/")
	public String principalPost(@RequestParam(name="manzanas", required=false)Integer manzanas,
			@RequestParam(name="melones", required=false)Integer melones,
			@RequestParam(name="peras", required=false)Integer peras, Model modelo) {
		
		if (manzanas != null) stock.modificar("manzanas", stock.getAll().get("manzanas")-manzanas);
		
		if (peras != null) stock.modificar("peras", stock.getAll().get("peras")-peras);
		
		if (melones != null) stock.modificar("melones", stock.getAll().get("melones")-melones);
		
		return "index";
	}
	
	
}
