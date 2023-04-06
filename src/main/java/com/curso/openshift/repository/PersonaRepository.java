/**
 * 
 */
package com.curso.openshift.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.openshift.model.Persona;

/**
 * 
 * <b> Interfaz del repositorio del cliente. </b>
 * 
 * @author Javier Lucero
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Javier Lucero $, $Date: 25 abr. 2022 $]
 *          </p>
 */
public interface PersonaRepository extends JpaRepository<Persona, Long> {

	Optional<Persona> findByIdentificacion(String id);

}
