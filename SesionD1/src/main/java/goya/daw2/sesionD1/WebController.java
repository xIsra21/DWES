package goya.daw2.sesionD1;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class WebController {
	
	private RepositorioQuizz repositorio;

    public WebController (RepositorioQuizz repositorio) {
    	this.repositorio = repositorio;
    }
	
    @GetMapping("/")
    public String showForm() {
        return "form";
    }

    @GetMapping("/form")
    public String formulario(@RequestParam(name = "nombre") String nombre,
                              Model modelo,
                              HttpSession session) {

        if (!compNom(nombre)) {
            modelo.addAttribute("error",
                    "Error en la sintaxis de datos Nombre: Len entre 2 y 30, no vacío y no numérico");
            return "form";
        }

        String textoCodificado = URLEncoder.encode(nombre, StandardCharsets.UTF_8);
        modelo.addAttribute("nombre", textoCodificado);

        session.setAttribute("nombre", nombre);

        return "form2";
    }

    @PostMapping("/form2")
    public String mostrarFormulario2(@RequestParam(name = "genero", required = false) String genero,
                                     Model modelo,
                                     HttpSession session) {

        if (genero == null || genero.trim().isEmpty()) {
            modelo.addAttribute("error", "Error, elige una opción");
            return "form2";
        }

        modelo.addAttribute("genero", genero);
        session.setAttribute("genero", genero);

        return "form3";
    }

    @PostMapping("/form3")
    public String formulario3(@RequestParam(name = "habitos", required = false) List<String> habitos,
                              Model modelo,
                              HttpSession session) {

        if (habitos == null || habitos.isEmpty()) {
            habitos = List.of("Ninguna seleccionada");
        }

        session.setAttribute("habitos", habitos);

        Map<String, Object> datos = new LinkedHashMap<>();
        datos.put("nombre", session.getAttribute("nombre"));
        datos.put("genero", session.getAttribute("genero"));
        datos.put("habitos", habitos);

        modelo.addAttribute("datos", datos);
        repositorio.save(new Quizz(habitos.size()));
        modelo.addAttribute("quizz",repositorio.findAll());
        return "results";
    }

    private boolean compNom(String nom) {
        String numeros = ".*[0-9].*";
        return !(nom.isEmpty() || nom.length() < 2 || nom.length() > 30 || nom.matches(numeros));
    }
}
