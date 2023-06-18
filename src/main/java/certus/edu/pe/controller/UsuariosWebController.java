package certus.edu.pe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import certus.edu.pe.modelo.Usuario;
import certus.edu.pe.servicios.UsuarioService;
@Controller
@RequestMapping("/usuarios")
public class UsuariosWebController {

	@Autowired // inyeccción de dependencia
	private UsuarioService usuarioServicio;
	
	@RequestMapping("/listarUsuario")
	public String listarUsuarios(Model model) {
		List<Usuario> listaUsuarios = usuarioServicio.buscarTodos();
		System.out.println("LISTA DE USUARIOS : " + listaUsuarios);
		model.addAttribute("listarUsuarios", listaUsuarios);
		return "/moduloUsuarios/listarUsuarios";
	}

	@RequestMapping("/nuevo")
	public String nuevoUsuarios(Model model) {
		Usuario usuarios = new Usuario();
		model.addAttribute("usuarios", usuarios);
		return "/moduloUsuarios/nuevoUsuarios";
		
	}
	
	@RequestMapping(value = "/guardar" , method = RequestMethod.POST)
	public String crearUsuario(@ModelAttribute("usuarios") Usuario usuarios) {
		    usuarioServicio.crearUsuario(usuarios);
		    return "redirect:/usuarios/listarUsuario";
	
	}
	
	@RequestMapping(value ="/eliminar/{id}")
	public String eliminarUsuario(@PathVariable(name = "id") int id) {
		  usuarioServicio.eliminarUsuario(id);
		 return "redirect:/usuarios/listarUsuario";
		
	}
	
	@RequestMapping(value = "/actualizar/{id}")
	public ModelAndView editarUsuario(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("/moduloUsuarios/editarUsuarios");
	    Usuario usuarios = usuarioServicio.buscarUsuarioPorId(id);
	    mav.addObject("usuarios", usuarios);
	    return mav;
	}
	
	
	
	
	
}

	