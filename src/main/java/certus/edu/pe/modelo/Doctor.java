package certus.edu.pe.modelo;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.NamedQueries;
//import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doctores")
@Data
@AllArgsConstructor
@NoArgsConstructor

//@NamedQueries({
//    @NamedQuery(name = "Doctores.findAll", query = "SELECT d FROM Doctores d")
//    , @NamedQuery(name = "Doctores.findByIdDoctor", query = "SELECT d FROM Doctores d WHERE d.idDoctor = :idDoctor")
//    , @NamedQuery(name = "Doctores.findByNombre", query = "SELECT d FROM Doctores d WHERE d.nombre = :nombre")
//    , @NamedQuery(name = "Doctores.findByApellido", query = "SELECT d FROM Doctores d WHERE d.apellido = :apellido")})

public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_doctor")
    private Integer idDoctor;
    
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_centro_medico", referencedColumnName = "id_centro_medico")
    private CentroMedico centroMedico;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_especialidad", referencedColumnName = "id_especialidad")    
    private Especialidad especialidad;
}
