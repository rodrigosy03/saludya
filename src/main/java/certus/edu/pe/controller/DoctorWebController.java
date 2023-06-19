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
import certus.edu.pe.modelo.Doctor;
import certus.edu.pe.modelo.CentroMedico;
import certus.edu.pe.modelo.Especialidad;
import certus.edu.pe.servicios.DoctorService;
import certus.edu.pe.servicios.CentroMedicoService;
import certus.edu.pe.servicios.EspecialidadService;
import java.util.List;

@RequestMapping("/doctores")
@Controller
public class DoctorWebController {
    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private CentroMedicoService centroMedicoService;
    
    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping("/listar")
    public String getDoctores(Model model) {
        List<Doctor> doctores = doctorService.buscarTodos();
        model.addAttribute("listaDoctores", doctores);
        return "moduloDoctor/listarDoctores";
    }

    @GetMapping("/agregar")
    public String nuevoDoctor(Model model) {
        Doctor doctor = new Doctor();
        model.addAttribute("doctor", doctor);
        
        List<CentroMedico> centrosMedicos = centroMedicoService.buscarTodos();
        model.addAttribute("listaCentrosMedicos", centrosMedicos);
        
        List<Especialidad> especialidades = especialidadService.buscarTodos();
        model.addAttribute("listaEspecialidades", especialidades);
        
        return "moduloDoctor/nuevoDoctor";
    }

    @PostMapping("/guardar")
    public String crearDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctorService.crearDoctor(doctor);
        return "redirect:/doctores/listar";
    }

    @RequestMapping(value = "/editar/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ModelAndView editarDoctor(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("moduloDoctor/editarDoctor");
        Doctor doctor = doctorService.buscarDoctorPorId(id);
        mav.addObject("doctor", doctor);
        
        List<CentroMedico> centrosMedicos = centroMedicoService.buscarTodos();
        mav.addObject("listaCentrosMedicos", centrosMedicos);
        
        List<Especialidad> especialidades = especialidadService.buscarTodos();
        mav.addObject("listaEspecialidades", especialidades);
        
        return mav;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDoctor(@PathVariable("id") int id, RedirectAttributes flash) {
        try {
            doctorService.eliminarDoctor(id);
            flash.addFlashAttribute("success", "Doctor eliminado correctamente.");
            return "redirect:/doctores/listar";
        } catch (DataIntegrityViolationException error) {
            flash.addFlashAttribute("error", "Este doctor no se puede eliminar debido a restricciones de clave foránea.");
            System.out.println(error);
            return "redirect:/doctores/listar";
        }
    }
}
