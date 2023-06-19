package certus.edu.pe.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import certus.edu.pe.modelo.Cita;
import certus.edu.pe.servicios.CitaService;


@RestController
@RequestMapping("/rest/citas")
public class CitaRestController {

@Autowired	
private CitaService servicio;

@GetMapping
public ResponseEntity<Object> buscarTodo()	{
	List<Cita> listaCitas = servicio.buscarTodas();
	
	  System.out.println("LISTA DE CITAS : " + listaCitas);
	  
	 return  new ResponseEntity<>(listaCitas, HttpStatus.OK);
	
}


@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
public ResponseEntity<Object> buscarPorId(@PathVariable("id") int id) {
	Cita citas = servicio.buscarCitaPorId(id);
	if ( citas == null)

		throw new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Citas no encontrada,id porporcionado no es correcto");
	return new ResponseEntity<Object>(citas, HttpStatus.OK);

}


@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
              consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
		)
public  ResponseEntity <Object> crear (@RequestBody Cita citas){
	
	    servicio.crearCita(citas);
	    return new ResponseEntity<Object>("Cita creada correctamente", HttpStatus.OK);

}

@PutMapping (value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
)
public ResponseEntity<Object> actualizar(@PathVariable("id") int id, @RequestBody Cita cita){
	
	servicio.actualizarCita(cita);
	return new ResponseEntity<Object>("Cita actualizada correctamente", HttpStatus.OK);
	
	
}


@DeleteMapping(value = "/{id}")
public ResponseEntity<Object> eliminar(@PathVariable("id") int id) {
    servicio.eliminarCita(id);
    return new ResponseEntity<Object>("Cita eliminada correctamente", HttpStatus.OK);
}

}
