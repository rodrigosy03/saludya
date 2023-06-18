package certus.edu.pe.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import certus.edu.pe.modelo.Cita;
import certus.edu.pe.repositorio.CitaRepository;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> buscarTodas() {
        return citaRepository.findAll();
    }

    public Cita crearCita(Cita cita) {
        return citaRepository.save(cita);
    }

    public Cita actualizarCita(Cita citaActualizada) {
        Cita citaExistente = citaRepository.findById(citaActualizada.getIdCita()).orElse(null);

        if (citaExistente != null) {
            citaExistente.setUsuario(citaActualizada.getUsuario());
            citaExistente.setDoctor(citaActualizada.getDoctor());
            citaExistente.setFechaHora(citaActualizada.getFechaHora());
            citaExistente.setDescripcion(citaActualizada.getDescripcion());

            return citaRepository.save(citaExistente);
        }

        return null; // Manejar el caso en el que la cita no exista
    }

    public Cita buscarCitaPorId(Integer idCita) {
        return citaRepository.findById(idCita).orElse(null);
    }

    public void eliminarCita(Integer idCita) {
        citaRepository.deleteById(idCita);
    }
}

