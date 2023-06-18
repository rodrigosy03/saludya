package certus.edu.pe.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import certus.edu.pe.modelo.Usuarios;

public interface UsuariosRepositorio extends CrudRepository <Usuarios, Integer> {

		@Query(value = "SELECT a FROM Usuarios a WHERE a.nombre =?1")
		public List<Usuarios> buscarUsuariosPorNombre(String nombre);
		
		@Query(value = "SELECT a FROM Usuarios a WHERE a.nombre like CONCAT(?1, '%')")
		public List<Usuarios> buscarsUsuariosPorNombre(String nombre);
		
		
}
