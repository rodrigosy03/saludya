package certus.edu.pe.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor

//@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM usuarios u"),
//		@NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM usuarios u WHERE u.idUsuario = :idUsuario"),
//		@NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM usuarios u WHERE u.nombre = :nombre"),
//		@NamedQuery(name = "Usuario.findByApellido", query = "SELECT u FROM usuarios u WHERE u.apellido = :apellido"),
//		@NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM usuarios u WHERE u.correo = :correo"),
//		@NamedQuery(name = "Usuario.findByContraseña", query = "SELECT u FROM usuarios u WHERE u.contrasena = :contraseña") })

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_usuario")
	private Integer idUsuario;
	
	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;
	
	@Basic(optional = false)
	@Column(name = "apellido")
	private String apellido;
	
	@Basic(optional = false)
	@Column(name = "correo")
	private String correo;
	
	@Basic(optional = false)
	@Column(name = "contraseña")
	private String contrasena;
}
