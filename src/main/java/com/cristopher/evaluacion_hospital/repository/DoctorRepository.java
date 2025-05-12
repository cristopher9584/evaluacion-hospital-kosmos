package com.cristopher.evaluacion_hospital.repository;

import com.cristopher.evaluacion_hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de acceso a datos para la entidad {@link Doctor}.
 *
 * Proporciona operaciones CRUD sobre los doctores registrados en el sistema.
 */
public interface DoctorRepository extends JpaRepository<Doctor, Long> {}
