package certus.edu.pe.controller;

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
import certus.edu.pe.modelo.Cita;
import certus.edu.pe.modelo.Doctor;
import certus.edu.pe.modelo.Usuario;
import certus.edu.pe.servicios.CitaService;
import certus.edu.pe.servicios.DoctorService;
import certus.edu.pe.servicios.UsuarioService;
import java.util.List;

@RequestMapping("/citas")
@Controller
public class CitaWebController {
    @Autowired
    private CitaService citaService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/listar")
    public String getCitas(Model model) {
        List<Cita> citas = citaService.buscarTodas();
        model.addAttribute("listaCitas", citas);
        return "moduloCita/listarCitas";
    }

    @GetMapping("/agregar")
    public String nuevaCita(Model model) {
        Cita cita = new Cita();
        model.addAttribute("cita", cita);
        
        List<Usuario> usuarios = usuarioService.buscarTodos();
        model.addAttribute("listaUsuarios", usuarios);
        
        List<Doctor> doctores = doctorService.buscarTodos();
        model.addAttribute("listaDoctores", doctores);
        
        return "moduloCita/nuevaCita";
    }

    @PostMapping("/guardar")
    public String crearCita(@ModelAttribute("cita") Cita cita) {
        citaService.crearCita(cita);
        return "redirect:/citas/listar";
    }

    @RequestMapping(value = "/editar/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ModelAndView editarCita(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("moduloCita/editarCita");
        Cita cita = citaService.buscarCitaPorId(id);
        mav.addObject("cita", cita);
        
        List<Usuario> usuarios = usuarioService.buscarTodos();
        mav.addObject("listaUsuarios", usuarios);
        
        List<Doctor> doctores = doctorService.buscarTodos();
        mav.addObject("listaDoctores", doctores);
        
        return mav;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable("id") int id, RedirectAttributes flash) {
        try {
            citaService.eliminarCita(id);
            flash.addFlashAttribute("success", "Cita eliminada correctamente.");
            return "redirect:/citas/listar";
        } catch (DataIntegrityViolationException error) {
            flash.addFlashAttribute("error", "Esta cita no se puede eliminar debido a restricciones de clave foránea.");
            System.out.println(error);
            return "redirect:/citas/listar";
        }
    }
}
