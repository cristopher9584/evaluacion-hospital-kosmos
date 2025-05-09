package com.cristopher.evaluacion_hospital.repository;

import com.cristopher.evaluacion_hospital.entity.Cita;
import com.cristopher.evaluacion_hospital.entity.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {}
