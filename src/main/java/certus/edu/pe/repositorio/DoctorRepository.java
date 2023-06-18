package certus.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import certus.edu.pe.modelo.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    // Puedes agregar métodos personalizados aquí, si lo deseas
}


