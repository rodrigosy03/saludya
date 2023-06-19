package certus.edu.pe.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import certus.edu.pe.modelo.CentroMedico;
import certus.edu.pe.repositorio.CentroMedicoRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CentroMedicoService {

    @Autowired
    private CentroMedicoRepository centroMedicoRepository;

    public List<CentroMedico> buscarTodos() {
        return centroMedicoRepository.findAll();
    }

    public CentroMedico crearCentroMedico(CentroMedico centroMedico) {
        return centroMedicoRepository.save(centroMedico);
    }

    public CentroMedico actualizarCentroMedico(CentroMedico centroMedicoActualizado) {
        CentroMedico centroMedicoExistente = centroMedicoRepository.findById(centroMedicoActualizado.getIdCentroMedico()).orElse(null);

        if (centroMedicoExistente != null) {
            centroMedicoExistente.setNombre(centroMedicoActualizado.getNombre());
            centroMedicoExistente.setDireccion(centroMedicoActualizado.getDireccion());
            centroMedicoExistente.setCiudad(centroMedicoActualizado.getCiudad());
            centroMedicoExistente.setTelefono(centroMedicoActualizado.getTelefono());

            return centroMedicoRepository.save(centroMedicoExistente);
        }

        return null; // Manejar el caso en el que el centro médico no exista
    }

    public CentroMedico buscarCentroMedicoPorId(Integer idCentroMedico) {
        return centroMedicoRepository.findById(idCentroMedico).orElse(null);
    }

    public void eliminarCentroMedico(Integer idCentroMedico) {
        centroMedicoRepository.deleteById(idCentroMedico);
    }
}


