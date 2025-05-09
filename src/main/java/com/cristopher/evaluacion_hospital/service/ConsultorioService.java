package com.cristopher.evaluacion_hospital.service;

import com.cristopher.evaluacion_hospital.entity.Consultorio;
import com.cristopher.evaluacion_hospital.entity.Doctor;
import com.cristopher.evaluacion_hospital.repository.ConsultorioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@AllArgsConstructor
public class ConsultorioService {

    private final ConsultorioRepository repository;


    public List<Consultorio> obtenerTodos() {
        List<Consultorio> doctores = repository.findAll();
        return doctores;
    }

}
