package com.cristopher.evaluacion_hospital.web.controller;

import com.cristopher.evaluacion_hospital.entity.Cita;
import com.cristopher.evaluacion_hospital.service.CitaService;
import com.cristopher.evaluacion_hospital.service.ConsultorioService;
import com.cristopher.evaluacion_hospital.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controlador que expone los endpoints relacionados con las citas médicas.
 *
 * Permite a los clientes realizar operaciones como crear, buscar o listar citas.
 */
@Controller
@RequestMapping("/citas")
@AllArgsConstructor
public class CitaController {

    private final CitaService citaService;

    private final DoctorService doctorService;

    private final ConsultorioService consultorioService;


    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("doctores", doctorService.obtenerTodos());
        model.addAttribute("consultorios", consultorioService.obtenerTodos());
        return "formulario_cita";
    }


    @GetMapping("/consulta")
    public String mostrarConsultaCitas() {
        return "consulta_citas";
    }

    @PostMapping
    public String guardarCita(@ModelAttribute("cita") Cita cita, Model model) {
        try {
            citaService.guardar(cita);
            return "redirect:/citas/nueva";
        } catch (IllegalArgumentException e) {
            model.addAttribute("mensajeError", e.getMessage());
            model.addAttribute("cita", cita);
            model.addAttribute("doctores", doctorService.obtenerTodos());
            model.addAttribute("consultorios", consultorioService.obtenerTodos());
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
            List<Cita> citas = citaService.buscarCitas(fecha, doctor, consultorio);
            model.addAttribute("citas", citas);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMensaje", e.getMessage());
        }
        return "consulta_citas";
    }


    @PostMapping("/{id}/eliminar")
    public String cancelarCita(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            citaService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensajeExito", "La cita fue cancelada correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
        }
        return "redirect:/citas/buscar";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Cita cita = citaService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de cita no válido: " + id));

        model.addAttribute("cita", cita);
        model.addAttribute("doctores", doctorService.obtenerTodos());
        model.addAttribute("consultorios", consultorioService.obtenerTodos());

        return "formulario_cita";
    }


}
