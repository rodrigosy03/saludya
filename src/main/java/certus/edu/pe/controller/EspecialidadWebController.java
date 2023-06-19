package certus.edu.pe.controller;

import certus.edu.pe.modelo.AreaMedica;
import certus.edu.pe.modelo.Especialidad;
import certus.edu.pe.servicios.EspecialidadService;
import certus.edu.pe.servicios.AreaMedicaService;

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

@RequestMapping("/especialidades")
@Controller
public class EspecialidadWebController {
    @Autowired
    private EspecialidadService especialidadService;
    
    @Autowired
    private AreaMedicaService areaMedicaService;

    @GetMapping("/listar")
    public String getEspecialidades(Model model) {
        List<Especialidad> especialidades = especialidadService.buscarTodos();
        model.addAttribute("listaEspecialidades", especialidades);
        return "moduloEspecialidad/listarEspecialidades";
    }

    @GetMapping("/agregar")
    public String nuevaEspecialidad(Model model) {
        Especialidad especialidad = new Especialidad();
        model.addAttribute("especialidad", especialidad);
        
        List<AreaMedica> areasMedicas = areaMedicaService.buscarTodos();
        model.addAttribute("listaAreasMedicas", areasMedicas);
        
        return "moduloEspecialidad/nuevaEspecialidad";
    }

    @PostMapping("/guardar")
    public String crearEspecialidad(@ModelAttribute("especialidad") Especialidad especialidad) {
        especialidadService.crearEspecialidad(especialidad);
        return "redirect:/especialidades/listar";
    }

    @RequestMapping(value = "/editar/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ModelAndView editarEspecialidad(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("moduloEspecialidad/editarEspecialidad");
        Especialidad especialidad = especialidadService.buscarEspecialidadPorId(id);
        mav.addObject("especialidad", especialidad);
        
        List<AreaMedica> areasMedicas = areaMedicaService.buscarTodos();
        mav.addObject("listaAreasMedicas", areasMedicas);
        
        return mav;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEspecialidad(@PathVariable("id") int id, RedirectAttributes flash) {
        try {
            especialidadService.eliminarEspecialidad(id);
            flash.addFlashAttribute("success", "Especialidad eliminada correctamente.");
            return "redirect:/especialidades/listar";
        } catch (DataIntegrityViolationException error) {
            flash.addFlashAttribute("error", "Esta especialidad no se puede eliminar debido a restricciones de clave foránea.");
            System.out.println(error);
            return "redirect:/especialidades/listar";
        }
    }
}
