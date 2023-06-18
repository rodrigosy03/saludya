package certus.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import certus.edu.pe.modelo.CentroMedico;

public interface CentroMedicoRepository extends JpaRepository<CentroMedico, Integer> {
    // Puedes agregar métodos personalizados aquí, si lo deseas
}


