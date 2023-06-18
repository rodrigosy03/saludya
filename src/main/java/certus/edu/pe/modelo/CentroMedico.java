package certus.edu.pe.modelo;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;	

@Entity
@Table(name = "centros_medicos")
@Data
@AllArgsConstructor
@NoArgsConstructor

//@NamedQueries({
//    @NamedQuery(name = "CentrosMedicos.findAll", query = "SELECT c FROM centros_medicos c")
//    , @NamedQuery(name = "CentrosMedicos.findByIdCentroMedico", query = "SELECT c FROM centros_medicos c WHERE c.idCentroMedico = :id_centro_medico")
//    , @NamedQuery(name = "CentrosMedicos.findByNombre", query = "SELECT c FROM centros_medicos c WHERE c.nombre = :nombre")
//    , @NamedQuery(name = "CentrosMedicos.findByDireccion", query = "SELECT c FROM centros_medicos c WHERE c.direccion = :direccion")
//    , @NamedQuery(name = "CentrosMedicos.findByCiudad", query = "SELECT c FROM centros_medicos c WHERE c.ciudad = :ciudad")
//    , @NamedQuery(name = "CentrosMedicos.findByTelefono", query = "SELECT c FROM centros_medicos c WHERE c.telefono = :telefono")})

public class CentroMedico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_centro_medico")
    private Integer idCentroMedico;
    
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    
    @Basic(optional = false)
    @Column(name = "ciudad")
    private String ciudad;
    
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
}
