package certus.edu.pe.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import certus.edu.pe.modelo.Doctor;
import certus.edu.pe.repositorio.DoctorRepository;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> buscarTodos() {
        return doctorRepository.findAll();
    }

    public Doctor crearDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor actualizarDoctor(Doctor doctorActualizado) {
        Doctor doctorExistente = doctorRepository.findById(doctorActualizado.getIdDoctor()).orElse(null);

        if (doctorExistente != null) {
            doctorExistente.setNombre(doctorActualizado.getNombre());
            doctorExistente.setApellido(doctorActualizado.getApellido());
            doctorExistente.setCentroMedico(doctorActualizado.getCentroMedico());
            doctorExistente.setEspecialidad(doctorActualizado.getEspecialidad());

            return doctorRepository.save(doctorExistente);
        }

        return null; // Manejar el caso en el que el doctor no exista
    }

    public Doctor buscarDoctorPorId(Integer idDoctor) {
        return doctorRepository.findById(idDoctor).orElse(null);
    }

    public void eliminarDoctor(Integer idDoctor) {
        doctorRepository.deleteById(idDoctor);
    }
}


