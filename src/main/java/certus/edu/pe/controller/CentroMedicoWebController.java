package certus.edu.pe.controller;

import certus.edu.pe.modelo.CentroMedico;
import certus.edu.pe.servicios.CentroMedicoService;

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

@RequestMapping("/centros-medicos")
@Controller
public class CentroMedicoWebController {
    @Autowired
    private CentroMedicoService centroMedicoServicio;

    @GetMapping("/listar")
    public String getCentrosMedicos(Model model) {
        List<CentroMedico> centrosMedicos = centroMedicoServicio.buscarTodos();
        model.addAttribute("listaCentrosMedicos", centrosMedicos);
        return "moduloCentroMedico/listarCentrosMedicos";
    }

    @GetMapping("/agregar")
    public String nuevoCentroMedico(Model model) {
        CentroMedico centroMedico = new CentroMedico();
        model.addAttribute("centroMedico", centroMedico);
        return "moduloCentroMedico/nuevoCentroMedico";
    }

    @PostMapping("/guardar")
    public String crearCentroMedico(@ModelAttribute("centroMedico") CentroMedico centroMedico) {
        centroMedicoServicio.crearCentroMedico(centroMedico);
        return "redirect:/centros-medicos/listar";
    }

    @RequestMapping(value = "/editar/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ModelAndView editarCentroMedico(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("moduloCentroMedico/editarCentroMedico");
        CentroMedico centroMedico = centroMedicoServicio.buscarCentroMedicoPorId(id);
        mav.addObject("centroMedico", centroMedico);
        return mav;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCentroMedico(@PathVariable("id") int id, RedirectAttributes flash) {
        try {
            centroMedicoServicio.eliminarCentroMedico(id);
            flash.addFlashAttribute("success", "Centro Médico eliminado correctamente.");
            return "redirect:/centros-medicos/listar";
        } catch (DataIntegrityViolationException error) {
            flash.addFlashAttribute("error", "Este Centro Médico no se puede eliminar debido a restricciones de clave foránea.");
            System.out.println(error);
            return "redirect:/centros-medicos/listar";
        }
    }
}
