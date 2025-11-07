package goya.daw2.sesionlogin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	static String usuario = "Israel", password = "12345";
	@GetMapping("/")
	public String principal(HttpSession sesion) {
		if(sesion.getAttribute("usuario") == null) {
			return "login";
		}
		if(!sesion.getAttribute("usuario").equals(usuario)){
			return "login";
		}
		return "index";
	}
	@PostMapping("/")
	public String principal2(@RequestParam(name="usuario", required=false)String usuario,
			@RequestParam(name="password", required=false)String password,
			HttpSession sesion) {
		if (usuario != null || password != null) {
			sesion.setAttribute("usuario", usuario);
			sesion.setAttribute("password", password);
		}		
		if(sesion.getAttribute("usuario") == null) {
			return "login";
		}
		if(!sesion.getAttribute("usuario").equals(usuario) ||
				!sesion.getAttribute("password").equals(password)) {
			return "login";
		}
		return "index";
	}
	@PostMapping("/login")
	public String login(@RequestParam(name="usuario")String usuario,
			@RequestParam(name="password")String password,
			Model modelo,
			HttpSession sesion) {
		sesion.setAttribute("usuario", usuario);
		sesion.setAttribute("password", password);
		if(sesion.getAttribute("usuario") == null) {
			return "login";
		}
		if(!sesion.getAttribute("usuario").equals(usuario) ||
				!sesion.getAttribute("password").equals(password)|| 
				sesion.getAttribute("usuario") == null) {
			return "login";
		}
		return "index";
	}
	@GetMapping("/pag1")
	public String pag1(HttpSession sesion) {
		if(sesion.getAttribute("usuario") == null) {
			return "login";
		}
		if(!sesion.getAttribute("usuario").equals(usuario) ||
				!sesion.getAttribute("password").equals(password)|| 
				sesion.getAttribute("usuario") == null) {
			return "login";
		}
		return "pag1";
	}
	
	@GetMapping("/pag2")
	public String pag2(HttpSession sesion) {
		if(sesion.getAttribute("usuario") == null) {
			return "login";
		}
		if(!sesion.getAttribute("usuario").equals(usuario) ||
				!sesion.getAttribute("password").equals(password)|| 
				sesion.getAttribute("usuario") == null) {
			return "login";
		}
		return "pag2";
	}
}
