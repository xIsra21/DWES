package goya.daw2.CarritoCompra;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorCarrito {
	Map<String, Integer> pedidos = new HashMap<String, Integer>();
	static RepositorioStock stock=new RepositorioStock();	
	public static void addStock() {
		stock.anadir("Manzanas", 15);
		stock.anadir("Peras", 5);
		stock.anadir("Melones", 5);
		stock.guardar();
	}
	@GetMapping("/")
	public String principal(Model modelo, HttpSession sesion) {
		sesion.setAttribute("pedidos", pedidos);
		addStock();
		System.out.println(stock.getAll());
		modelo.addAttribute("stock", stock.getAll());
		
		return "index";
	}
	@PostMapping("/")
	public String principalPost(@RequestParam(name="manzanas", required=false)Integer manzanas,
			@RequestParam(name="melones", required=false)Integer melones,
			@RequestParam(name="peras", required=false)Integer peras, 
			Model modelo,
			HttpSession sesion) {
		if (sesion.getAttribute("manzanas")!=null) {
			sesion.setAttribute("manzanas", manzanas+(Integer)sesion.getAttribute("manzanas"));
		} else {
			sesion.setAttribute("manzanas", manzanas);
		}
		if (sesion.getAttribute("peras")!=null) {
			sesion.setAttribute("peras", peras+Integer.parseInt((String) sesion.getAttribute("peras")));
		} else {
			sesion.setAttribute("peras", peras);
		}
		if (sesion.getAttribute("melones")!=null) {
			sesion.setAttribute("melones", melones+Integer.parseInt((String) sesion.getAttribute("melones")));
		} else {
			sesion.setAttribute("melones", melones);
		}
		
		Integer total=0;
		
		if (manzanas != null) {
			
			if (manzanas > 0) stock.modificar("Manzanas", stock.getAll().get("Manzanas")-manzanas);
			
			if (manzanas > stock.getAll().get("Manzanas")) {
				pedidos.put("Manzanas", stock.getAll().get("Manzanas"));
			} else {
				pedidos.put("Manzanas", (Integer)sesion.getAttribute("manzanas"));
			}
			total+=pedidos.get("Manzanas");
		}
		
		if (peras != null) {
			
			if (peras > 0) stock.modificar("Peras", stock.getAll().get("Peras")-peras);
			
			if (peras > stock.getAll().get("Peras")) {
				pedidos.put("Peras", stock.getAll().get("Peras"));
			} else {
				pedidos.put("Manzanas", Integer.parseInt((String) sesion.getAttribute("peras")));
			}
			total+=pedidos.get("Peras");
		}
		
		if (melones != null) {
			
			if (melones > 0) stock.modificar("Melones", stock.getAll().get("Melones")-melones);
			
			if (melones > stock.getAll().get("Melones")) {
				pedidos.put("Melones", stock.getAll().get("Melones"));
			} else {
				pedidos.put("Manzanas", Integer.parseInt((String) sesion.getAttribute("melones")));
			}
			total+=pedidos.get("Melones");
		}
		stock.guardar();
		
		modelo.addAttribute("stock", stock.getAll());
		modelo.addAttribute("productos", pedidos);
		modelo.addAttribute("total",total);

		return "index";
	}
	
	
}
