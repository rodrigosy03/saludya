package certus.edu.pe.controller;

import certus.edu.pe.modelo.Usuario;
import certus.edu.pe.servicios.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/usuarios")
@Controller
public class UsuarioWebController {
    @Autowired
    private UsuarioService usuarioServicio;

    @GetMapping("/listar")
    public String getUsuarios(Model model) {
        List<Usuario> usuarios = usuarioServicio.buscarTodos();
        model.addAttribute("listaUsuarios", usuarios);
        return "moduloUsuario/listarUsuarios";
    }

    @GetMapping("/agregar")
    public String nuevoUsuario(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "moduloUsuario/nuevoUsuario";
    }

    @PostMapping("/guardar")
    public String crearUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioServicio.crearUsuario(usuario);
        return "redirect:/usuarios/listar";
    }

    @RequestMapping(value = "/editar/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ModelAndView editarUsuario(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("moduloUsuario/editarUsuario");
        Usuario usuario = usuarioServicio.buscarUsuarioPorId(id);
        mav.addObject("usuario", usuario);
        return mav;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") int id, RedirectAttributes flash) {
        try {
            usuarioServicio.eliminarUsuario(id);
            flash.addFlashAttribute("success", "Usuario eliminado correctamente.");
            return "redirect:/usuarios/listar";
        } catch (DataIntegrityViolationException error) {
            flash.addFlashAttribute("error", "Este usuario no se puede eliminar debido a restricciones de clave foránea.");
            System.out.println(error);
            return "redirect:/usuarios/listar";
        }
    }
}



	