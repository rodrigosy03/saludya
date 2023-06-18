package certus.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import certus.edu.pe.modelo.AreaMedica;

public interface AreaMedicaRepository extends JpaRepository<AreaMedica, Integer> {
    // Puedes agregar métodos personalizados aquí, si lo deseas
}
