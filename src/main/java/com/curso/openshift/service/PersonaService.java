/**
 * 
 */
package com.curso.openshift.service;

import java.util.Optional;

import com.curso.openshift.model.Persona;

/**
 * 
 * <b> Interfaz del servicio para el cliente. </b>
 * 
 * @author Javier Lucero
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Javier Lucero $, $Date: 25 abr. 2022 $]
 *          </p>
 */
public interface PersonaService {
	Persona create(Persona cliente);

	Optional<Persona> obtenerPorIdentificacion(String identificacion);

}
