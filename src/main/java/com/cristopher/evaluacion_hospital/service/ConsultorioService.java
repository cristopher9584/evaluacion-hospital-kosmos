package com.cristopher.evaluacion_hospital.service;

import com.cristopher.evaluacion_hospital.entity.Consultorio;
import com.cristopher.evaluacion_hospital.repository.ConsultorioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio que encapsula la lógica de negocio para el manejo de consultorios médicos.
 *
 * Se encarga de registrar, consultar y modificar información de los consultorios.
 */
@Service
@AllArgsConstructor
public class ConsultorioService {

    private final ConsultorioRepository repository;


    public List<Consultorio> obtenerTodos(){
        return repository.findAll();
    }

}
