package certus.edu.pe.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "especialidades")
@Data
@AllArgsConstructor
@NoArgsConstructor

//@NamedQueries({
//    @NamedQuery(name = "Especialidades.findAll", query = "SELECT e FROM Especialidades e")
//    , @NamedQuery(name = "Especialidades.findByIdEspecialidad", query = "SELECT e FROM Especialidades e WHERE e.idEspecialidad = :idEspecialidad")
//    , @NamedQuery(name = "Especialidades.findByNombre", query = "SELECT e FROM Especialidades e WHERE e.nombre = :nombre")})

public class Especialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_especialidad")
    private Integer idEspecialidad;
    
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_area_medica", referencedColumnName = "id_area_medica")
    private AreaMedica areaMedica;
}
