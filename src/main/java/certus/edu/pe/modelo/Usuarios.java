package certus.edu.pe.modelo;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

//conectando a la bd
@Entity
@Table(name="usuarios")

//named queries
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", 			query = "SELECT p FROM Usuarios p"),
    @NamedQuery(name = "Usuarios.findByIdusuario", 	query = "SELECT p FROM Usuarios p WHERE p.idusuario = :idusuario"),
    @NamedQuery(name = "Usuarios.findByNombre", 	query = "SELECT p FROM Usuarios p WHERE p.nombre 	= :nombre"),
    @NamedQuery(name = "Usuarios.findByApellido", 	query = "SELECT p FROM Usuarios p WHERE p.apellido 	= :apellido"),
    @NamedQuery(name = "Usuarios.findByCorreo", 	query = "SELECT p FROM Usuarios p WHERE p.correo 	= :correo"),
    @NamedQuery(name = "Usuarios.findByPassword", 	query = "SELECT p FROM Usuarios p WHERE p.password 	= :password")
})
public class Usuarios implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = true)
	private Integer idusuario;
	
	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "correo")
	private String correo;
		
	@Column(name = "password")
	private String password;

	public Usuarios() {
		
	}

	public Usuarios(Integer idusuario, String nombre, String apellido, String correo, String password) {
		super();
		this.idusuario = idusuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.password = password;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Usuarios [idusuario=" + idusuario + ", nombre=" + nombre + ", apellido=" + apellido + ", correo="
				+ correo + ", password=" + password + "]";
	}
	
	
	
	
	

	
	
}