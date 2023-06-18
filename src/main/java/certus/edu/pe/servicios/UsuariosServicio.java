package certus.edu.pe.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import certus.edu.pe.modelo.Usuarios;
import certus.edu.pe.repositorio.UsuariosRepositorio;

@Service
@Transactional
public class UsuariosServicio {

	@Autowired // Inyección de dependencia
	private UsuariosRepositorio repositorio;
	
	public UsuariosServicio() {
		
	}
	
	public List<Usuarios> buscarTodo() {
		return (List<Usuarios>) repositorio.findAll();
	}
	
	public Usuarios crear(Usuarios usuarios) {
		return repositorio.save(usuarios);
	}
	
	public Usuarios actualizar(Usuarios usuariosActualizar) {
		
		// Se busca la pelicula por id
		Usuarios usuarioActual = repositorio.findById(usuariosActualizar.getIdusuario()).get();
		
		// Se actualizan las variables
		
		usuarioActual.setIdusuario(usuariosActualizar.getIdusuario());
		usuarioActual.setNombre(usuariosActualizar.getNombre());
		usuarioActual.setApellido(usuariosActualizar.getApellido());
		usuarioActual.setCorreo(usuariosActualizar.getCorreo());
		usuarioActual.setPassword(usuariosActualizar.getPassword());
		
		Usuarios usuariosActualizado = repositorio.save(usuarioActual);
		return usuariosActualizado;
	}
	
	// Buscar por ID	
	public Usuarios buscarPorId(Integer id) {
		
		return repositorio.findById(id).get();
	}
	
	// Borrar por ID
	public void borrarPorId(Integer id) {
		repositorio.deleteById(id);
	}

	
}
