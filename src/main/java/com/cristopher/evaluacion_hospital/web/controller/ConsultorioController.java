package com.cristopher.evaluacion_hospital.web.controller;

import com.cristopher.evaluacion_hospital.service.ConsultorioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 * Controlador REST que maneja los endpoints para los consultorios médicos.
 *
 * Facilita la gestión de consultorios desde el cliente (creación, listado, etc.).
 */
@Controller
@AllArgsConstructor
public class ConsultorioController {

    private final ConsultorioService consultorioService;



}
