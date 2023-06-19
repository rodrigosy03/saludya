package certus.edu.pe.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import certus.edu.pe.modelo.CentroMedico;
import certus.edu.pe.servicios.CentroMedicoService;


@RestController
@RequestMapping("/rest/centros")
public class rest {

@Autowired	
private CentroMedicoService servicio;

@GetMapping
public ResponseEntity<Object> buscarTodo()	{
	List<CentroMedico> listaCentreos = servicio.buscarTodos();
	
	  System.out.println("LISTA DE CITAS : " + listaCentreos);
	  
	 return  new ResponseEntity<>(listaCentreos, HttpStatus.OK);
	
}



}
