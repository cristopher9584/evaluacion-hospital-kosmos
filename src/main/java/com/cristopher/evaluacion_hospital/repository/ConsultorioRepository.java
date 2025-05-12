package com.cristopher.evaluacion_hospital.repository;

import com.cristopher.evaluacion_hospital.entity.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de acceso a datos para la entidad {@link Consultorio}.
 *
 * Permite gestionar la persistencia y consultas sobre los consultorios médicos.
 */
public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {}
