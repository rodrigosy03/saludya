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

import certus.edu.pe.modelo.Usuarios;
import certus.edu.pe.servicios.UsuariosServicio;
@Controller
@RequestMapping("/usuarios")
public class UsuariosWebController {

	@Autowired // inyeccción de dependencia
	private UsuariosServicio servicio;
	
	@RequestMapping("/listarUsuario")
	public String listarUsuarios(Model model) {
		List<Usuarios> listaUsuarios = servicio.buscarTodo();
		System.out.println("LISTA DE USUARIOS : " + listaUsuarios);
		model.addAttribute("listarUsuarios", listaUsuarios);
		return "/moduloUsuarios/listarUsuarios";
	}

	@RequestMapping("/nuevo")
	public String nuevoUsuarios(Model model) {
		Usuarios usuarios = new Usuarios();
		model.addAttribute("usuarios", usuarios);
		return "/moduloUsuarios/nuevoUsuarios";
		
	}
	
	@RequestMapping(value = "/guardar" , method = RequestMethod.POST)
	public String crearUsuario(@ModelAttribute("usuarios") Usuarios usuarios) {
		    servicio.crear(usuarios);
		    return "redirect:/usuarios/listarUsuario";
	
	}
	
	@RequestMapping(value ="/eliminar/{id}")
	public String eliminarUsuario(@PathVariable(name = "id") int id) {
		  servicio.borrarPorId(id);
		 return "redirect:/usuarios/listarUsuario";
		
	}
	
	@RequestMapping(value = "/actualizar/{id}")
	public ModelAndView editarUsuario(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("/moduloUsuarios/editarUsuarios");
	    Usuarios usuarios = servicio.buscarPorId(id);
	    mav.addObject("usuarios", usuarios);
	    return mav;
	}
	
	
	
	
	
}

	