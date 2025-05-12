package com.cristopher.evaluacion_hospital.web.controller;

import com.cristopher.evaluacion_hospital.entity.Cita;
import com.cristopher.evaluacion_hospital.service.CitaService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@AllArgsConstructor
public class CitaController {

    private final CitaService citaService;


    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cita", new Cita());
        return "formulario_cita";
    }

    @PostMapping
    public String guardarCita(@ModelAttribute("cita") Cita cita, Model model) {
        try {
            citaService.guardar(cita);
            return "redirect:/citas";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMensaje", e.getMessage());
            model.addAttribute("cita", cita);
            return "formulario_cita";
        }
    }

    @GetMapping("/buscar")
    public String buscarCitas(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(required = false) String doctor,
            @RequestParam(required = false) String consultorio,
            Model model) {
        try {
            LocalDateTime fechaHora = (fecha != null) ? fecha.atStartOfDay() : null;

            List<Cita> citas = citaService.buscarCitas(fechaHora, doctor, consultorio);
            model.addAttribute("citas", citas);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMensaje", e.getMessage());
        }
        return "consulta_citas";
    }


    @PostMapping("/citas/{id}/eliminar")
    public String cancelarCita(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            citaService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensajeExito", "La cita fue cancelada correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
        }
        return "redirect:/citas/buscar";
    }


}
