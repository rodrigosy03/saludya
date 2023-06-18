package certus.edu.pe.controller;

import certus.edu.pe.modelo.AreaMedica;
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

@RequestMapping("/areas-medicas")
@Controller
public class AreaMedicaWebController {
    @Autowired
    private AreaMedicaService areaMedicaService;

    @GetMapping("/listar")
    public String getAreasMedicas(Model model) {
        List<AreaMedica> areasMedicas = areaMedicaService.buscarTodos();
        model.addAttribute("listaAreasMedicas", areasMedicas);
        return "moduloAreaMedica/listarAreasMedicas";
    }

    @GetMapping("/agregar")
    public String nuevaAreaMedica(Model model) {
        AreaMedica areaMedica = new AreaMedica();
        model.addAttribute("areaMedica", areaMedica);
        return "moduloAreaMedica/nuevaAreaMedica";
    }

    @PostMapping("/guardar")
    public String crearAreaMedica(@ModelAttribute("areaMedica") AreaMedica areaMedica) {
        areaMedicaService.crearAreaMedica(areaMedica);
        return "redirect:/areas-medicas/listar";
    }

    @RequestMapping(value = "/editar/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ModelAndView editarAreaMedica(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("moduloAreaMedica/editarAreaMedica");
        AreaMedica areaMedica = areaMedicaService.buscarAreaMedicaPorId(id);
        mav.addObject("areaMedica", areaMedica);
        return mav;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAreaMedica(@PathVariable("id") int id, RedirectAttributes flash) {
        try {
            areaMedicaService.eliminarAreaMedica(id);
            flash.addFlashAttribute("success", "Área Médica eliminada correctamente.");
            return "redirect:/areas-medicas/listar";
        } catch (DataIntegrityViolationException error) {
            flash.addFlashAttribute("error", "Esta Área Médica no se puede eliminar debido a restricciones de clave foránea.");
            System.out.println(error);
            return "redirect:/areas-medicas/listar";
        }
    }
}
