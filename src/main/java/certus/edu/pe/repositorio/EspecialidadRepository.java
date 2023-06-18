package certus.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import certus.edu.pe.modelo.Especialidad;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {
    // Puedes agregar métodos personalizados aquí, si lo deseas
}

