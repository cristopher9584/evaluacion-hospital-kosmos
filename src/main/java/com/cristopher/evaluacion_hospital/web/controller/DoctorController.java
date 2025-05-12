package com.cristopher.evaluacion_hospital.web.controller;

import com.cristopher.evaluacion_hospital.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;


/**
 * Controlador REST que expone los endpoints relacionados con los doctores.
 *
 * Permite realizar operaciones como listar, registrar o actualizar médicos.
 */
@Controller
@AllArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

}
