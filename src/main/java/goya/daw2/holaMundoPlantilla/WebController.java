package goya.daw2.holaMundoPlantilla;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
public class WebController {
	static Map<String, String> datos = new LinkedHashMap<String, String>();
	
	@GetMapping("/")
	public String showForm(Model modelo) {
		
		return "form";
	}
	
	@PostMapping("/form")
	
	public String formulario(@RequestParam(name="nombre") String nombre, Model modelo) {
		if (!compNom(nombre)) {
			modelo.addAttribute("error", "Error en la sintaxis de datos Nombre: Len entre 2 y 30, no vacio y no numerico");
			return "form";
		} else {
			modelo.addAttribute("nombre", nombre);
			if (datos.containsKey("nombre")) {
				datos.remove("nombre");
			}
			datos.put("nombre", nombre);
			return "form2";
		}
	}
	
	@PostMapping("/form2")
    public String mostrarFormulario2(@RequestParam(name="genero", required=false) String genero, Model modelo) {
		if (genero == null || genero.trim().isEmpty()) {
			modelo.addAttribute("error", "Error, elige una opcion");
			return "form2";
		} else {
			modelo.addAttribute("genero", genero);
			if (datos.containsKey("genero")) {
				datos.remove("genero");
			}
			datos.put("genero", genero);
			return "form3";
		}
    }

	@PostMapping("/form3")
	public String formulario2(@RequestParam(name="habitos", required=false) List<String> habitos, Model modelo) {

	    if (habitos == null || habitos.isEmpty()) {
	        habitos = List.of("Ninguna seleccionada");
	    }

	    modelo.addAttribute("habitos", habitos);
	    modelo.addAttribute("datos", datos);
	    if (datos.containsKey("habitos")) {
			datos.remove("habitos");
		}
	    datos.put("habitos", String.join(", ", habitos));

	    return "results";
	}

    
	private boolean compNom(String nom) {
		String numeros="0123456789";
		for (int i=0;i<numeros.length();i++) {
			if(nom.isEmpty()||nom.length()>30||nom.length()<2||nom.contains(numeros.substring(i, i))) {
				return false;
			}
		}
		return true;
	}
	
	
}
