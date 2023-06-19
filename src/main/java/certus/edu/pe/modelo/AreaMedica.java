package certus.edu.pe.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "areas_medicas")
@Data
@AllArgsConstructor
@NoArgsConstructor

//@NamedQueries({
//    @NamedQuery(name = "AreasMedicas.findAll", query = "SELECT a FROM AreasMedicas a")
//    , @NamedQuery(name = "AreasMedicas.findByIdAreaMedica", query = "SELECT a FROM AreasMedicas a WHERE a.idAreaMedica = :idAreaMedica")
//    , @NamedQuery(name = "AreasMedicas.findByNombre", query = "SELECT a FROM AreasMedicas a WHERE a.nombre = :nombre")})

public class AreaMedica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_area_medica")
    private Integer idAreaMedica;
    
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
}
