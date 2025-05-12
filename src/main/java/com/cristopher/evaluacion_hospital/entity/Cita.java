package com.cristopher.evaluacion_hospital.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entidad que representa una cita médica en el sistema.
 *
 * Contiene información sobre la fecha de la cita, el doctor que la atiende
 * y el consultorio asignado.
 */
@Entity
@Getter
@Setter
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paciente;

    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "consultorio_id")
    private Consultorio consultorio;

}
