package com.cristopher.evaluacion_hospital.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad que representa a un doctor registrado en el sistema.
 *
 * Incluye datos personales y profesionales del médico.
 */
@Entity
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String especialidad;

}
