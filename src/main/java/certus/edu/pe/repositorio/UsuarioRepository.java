package certus.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import certus.edu.pe.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Agregar métodos personalizados para consultas específicas, si lo deseas
}

