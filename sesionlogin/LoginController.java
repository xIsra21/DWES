package goya.daw2.sesionlogin;

import java.util.HashMap;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	static HashMap<String, String> usuarios = new HashMap<String, String>();
	static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	public void addUsers() {
		usuarios.put("Israel",encoder.encode("12345"));
		usuarios.put("Javier",encoder.encode("puche"));
	}
	
	
	@GetMapping("/")
	public String principal() {
		
		return "/login";
	}
	
	@PostMapping("/")
	public String principal2(@RequestParam(name="usuario", required=false)String usuario,
			@RequestParam(name="password", required=false)String password,
			HttpSession sesion,
			Model modelo) {
		addUsers();
		if (usuario != null || password != null) {
			sesion.setAttribute("usuario", usuario);
			sesion.setAttribute("password", password);
			if(sesion.getAttribute("usuario") == null) {
				modelo.addAttribute("error","Usuario o COntraseña Incorrectos");
				return "/login";
			}
			if(!usuarios.containsKey(usuario) ||
					!encoder.matches(password, usuarios.get(usuario))) {
				modelo.addAttribute("error","Usuario o COntraseña Incorrectos");
				return "/login";
			}
			return "index";
		}
		return "index";
	}
	
	@GetMapping("/login")
	public String login(HttpSession sesion,
			Model modelo) {
		addUsers();
		if(sesion.getAttribute("usuario") == null) {
			
			return "/login";
		}
		if(usuarios.containsKey(sesion.getAttribute("usuario")) &&
				!encoder.matches((CharSequence) sesion.getAttribute("password"), usuarios.get(sesion.getAttribute("usuario")))) {
			modelo.addAttribute("error","");
			return "index";
		}
		modelo.addAttribute("error","Usuario o COntraseña Incorrectos");
		return "login";
	}
	
	@GetMapping("/pag1")
	public String pag1(HttpSession sesion) {
		addUsers();
		if(sesion.getAttribute("usuario") == null) {
			return "/login";
		}
		if(!usuarios.containsKey(sesion.getAttribute("usuario")) &&
				!encoder.matches((CharSequence) sesion.getAttribute("password"), usuarios.get(sesion.getAttribute("usuario")))|| 
				sesion.getAttribute("usuario") == null) {
			return "/login";
		}
		return "pag1";
	}
	
	@GetMapping("/pag2")
	public String pag2(HttpSession sesion) {
		addUsers();
		if(sesion.getAttribute("usuario") == null) {
			return "/login";
		}
		if(!usuarios.containsKey(sesion.getAttribute("usuario")) &&
				!encoder.matches((CharSequence) sesion.getAttribute("password"), usuarios.get(sesion.getAttribute("usuario")))|| 
				sesion.getAttribute("usuario") == null) {
			return "/login";
		}
		return "pag2";
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession sesion) {
		addUsers();
		sesion.invalidate();
		return "/login";
	}
}
