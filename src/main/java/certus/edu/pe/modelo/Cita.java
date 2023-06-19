package certus.edu.pe.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "citas")
@Data
@AllArgsConstructor
@NoArgsConstructor

//@NamedQueries({
//    @NamedQuery(name = "Citas.findAll", query = "SELECT c FROM Citas c")
//    , @NamedQuery(name = "Citas.findByIdCita", query = "SELECT c FROM Citas c WHERE c.idCita = :idCita")
//    , @NamedQuery(name = "Citas.findByFechaHora", query = "SELECT c FROM Citas c WHERE c.fechaHora = :fechaHora")
//    , @NamedQuery(name = "Citas.findByDescripcion", query = "SELECT c FROM Citas c WHERE c.descripcion = :descripcion")})

public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cita")
    private Integer idCita;
    
    @Basic(optional = false)
    @Column(name = "fechaHora")
    private LocalDateTime fechaHora;
    
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_doctor", referencedColumnName = "id_doctor")
    private Doctor doctor;
}
