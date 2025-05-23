package com.cristopher.evaluacion_hospital.service;

import com.cristopher.evaluacion_hospital.entity.Consultorio;
import com.cristopher.evaluacion_hospital.entity.Doctor;
import com.cristopher.evaluacion_hospital.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio que contiene la lógica de negocio relacionada con los doctores.
 *
 * Permite gestionar la información médica y operativa del personal doctor.
 */
@Service
@AllArgsConstructor
public class DoctorService {

    private final DoctorRepository repository;


    public List<Doctor> obtenerTodos(){
        return repository.findAll();
    }

}
