/**
 * 
 */
package com.curso.openshift.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.openshift.model.Persona;
import com.curso.openshift.repository.PersonaRepository;
import com.curso.openshift.service.PersonaService;

/**
 * 
 * <b> Servicio para el cliente. </b>
 * 
 * @author Javier Lucero
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Javier Lucero $, $Date: 25 abr. 2022 $]
 *          </p>
 */
@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public Persona create(Persona persona) {
		return personaRepository.save(persona);
	}

	@Override
	public Optional<Persona> obtenerPorIdentificacion(String identificacion) {
		return personaRepository.findByIdentificacion(identificacion);
	}

}
