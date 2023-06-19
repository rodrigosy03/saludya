package certus.edu.pe.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import certus.edu.pe.modelo.Usuario;
import certus.edu.pe.repositorio.UsuarioRepository;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Usuario usuarioActualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(usuarioActualizado.getIdUsuario()).orElse(null);

        if (usuarioExistente != null) {
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setApellido(usuarioActualizado.getApellido());
            usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
            usuarioExistente.setContrasena(usuarioActualizado.getContrasena());

            return usuarioRepository.save(usuarioExistente);
        }

        return null; // Manejar el caso en el que el usuario no exista
    }

    public Usuario buscarUsuarioPorId(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario).orElse(null);
    }

    public void eliminarUsuario(Integer idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }
}

