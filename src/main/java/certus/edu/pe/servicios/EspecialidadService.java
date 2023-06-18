package certus.edu.pe.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import certus.edu.pe.modelo.Especialidad;
import certus.edu.pe.repositorio.EspecialidadRepository;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public List<Especialidad> buscarTodos() {
        return especialidadRepository.findAll();
    }

    public Especialidad crearEspecialidad(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    public Especialidad actualizarEspecialidad(Especialidad especialidadActualizada) {
        Especialidad especialidadExistente = especialidadRepository.findById(especialidadActualizada.getIdEspecialidad()).orElse(null);

        if (especialidadExistente != null) {
            especialidadExistente.setNombre(especialidadActualizada.getNombre());
            especialidadExistente.setAreaMedica(especialidadActualizada.getAreaMedica());

            return especialidadRepository.save(especialidadExistente);
        }

        return null; // Manejar el caso en el que la especialidad no exista
    }

    public Especialidad buscarEspecialidadPorId(Integer idEspecialidad) {
        return especialidadRepository.findById(idEspecialidad).orElse(null);
    }

    public void eliminarEspecialidad(Integer idEspecialidad) {
        especialidadRepository.deleteById(idEspecialidad);
    }
}


