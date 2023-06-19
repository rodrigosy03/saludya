package certus.edu.pe.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import certus.edu.pe.modelo.AreaMedica;
import certus.edu.pe.repositorio.AreaMedicaRepository;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AreaMedicaService {

    @Autowired
    private AreaMedicaRepository areaMedicaRepository;

    public List<AreaMedica> buscarTodos() {
        return areaMedicaRepository.findAll();
    }

    public AreaMedica crearAreaMedica(AreaMedica areaMedica) {
        return areaMedicaRepository.save(areaMedica);
    }

    public AreaMedica actualizarAreaMedica(AreaMedica areaMedicaActualizada) {
        AreaMedica areaMedicaExistente = areaMedicaRepository.findById(areaMedicaActualizada.getIdAreaMedica()).orElse(null);

        if (areaMedicaExistente != null) {
            areaMedicaExistente.setNombre(areaMedicaActualizada.getNombre());

            return areaMedicaRepository.save(areaMedicaExistente);
        }

        return null; // Manejar el caso en el que el área médica no exista
    }

    public AreaMedica buscarAreaMedicaPorId(Integer idAreaMedica) {
        return areaMedicaRepository.findById(idAreaMedica).orElse(null);
    }

    public void eliminarAreaMedica(Integer idAreaMedica) {
        areaMedicaRepository.deleteById(idAreaMedica);
    }
}


