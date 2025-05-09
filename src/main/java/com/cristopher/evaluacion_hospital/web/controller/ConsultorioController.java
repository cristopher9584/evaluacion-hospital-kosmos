package com.cristopher.evaluacion_hospital.web.controller;

import com.cristopher.evaluacion_hospital.entity.Consultorio;
import com.cristopher.evaluacion_hospital.service.ConsultorioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ConsultorioController {

    private final ConsultorioService consultorioService;

    @GetMapping("/consultorios")
    public String verConsultorios(Model model) {
        List<Consultorio> consultorios = consultorioService.obtenerTodos();
        model.addAttribute("consultorios", consultorios);
        return "consultorios"; // consultorios.html
    }

}
