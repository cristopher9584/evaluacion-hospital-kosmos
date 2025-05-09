package com.cristopher.evaluacion_hospital.service;

import com.cristopher.evaluacion_hospital.entity.Doctor;
import com.cristopher.evaluacion_hospital.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService {

    private final DoctorRepository repository;


    public List<Doctor> obtenerTodos() {
        List<Doctor> doctores = repository.findAll();
        return doctores;
    }

}
