package com.example;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.openshift.dto.PersonaEntradaDto;
import com.curso.openshift.model.Persona;
import com.curso.openshift.service.PersonaService;

@SpringBootApplication
public class SpringSampleAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSampleAppApplication.class, args);
	}
}

@RestController
@RequestMapping("/")
@RefreshScope
@Component
class HomeRestController {
	private static final Logger log = LoggerFactory.getLogger(HomeRestController.class);
	@Autowired
	private PersonaService service;

	boolean healthy = true;
	String hostname = "";

	public HomeRestController() {
		try {
			hostname = "Hello World from " + InetAddress.getLocalHost().getHostName().toString();
		} catch (UnknownHostException ex) {
			hostname = "error";
		}
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody PersonaEntradaDto personaEntradaDto) {
		try {

			Optional<Persona> clienteEncontrado = service
					.obtenerPorIdentificacion(personaEntradaDto.getIdentificacion());
			if (clienteEncontrado.isPresent()) {
				return new ResponseEntity<>("Cliente ya se encuentra registrado", HttpStatus.BAD_REQUEST);
			} else {
				Persona cliente = new Persona();
				cliente.setNombre(personaEntradaDto.getNombre());
				cliente.setIdentificacion(personaEntradaDto.getIdentificacion());
				Persona personaGuardado = service.create(cliente);
				return new ResponseEntity<Persona>(personaGuardado, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<?> obtenerCliente(@RequestBody PersonaEntradaDto personaEntradaDto) {
		try {
			Optional<Persona> clienteEncontrado = service
					.obtenerPorIdentificacion(personaEntradaDto.getIdentificacion());
			if (clienteEncontrado.isPresent()) {
				return new ResponseEntity<Persona>(clienteEncontrado.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No existe la persona con el parametro", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
