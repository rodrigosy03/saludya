package certus.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import certus.edu.pe.modelo.Cita;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
    // Puedes agregar métodos personalizados aquí, si lo deseas
}


