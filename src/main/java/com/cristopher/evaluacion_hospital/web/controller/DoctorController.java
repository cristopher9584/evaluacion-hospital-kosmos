package com.cristopher.evaluacion_hospital.web.controller;

import com.cristopher.evaluacion_hospital.entity.Doctor;
import com.cristopher.evaluacion_hospital.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@AllArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/doctores")
    public String verDoctores(Model model) {
        List<Doctor> doctores = doctorService.obtenerTodos();
        model.addAttribute("doctores", doctores);
        return "doctores";
    }

}
