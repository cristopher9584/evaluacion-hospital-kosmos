package com.cristopher.evaluacion_hospital.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad que representa un consultorio médico.
 *
 * Contiene información de id, piso y número del consultorio.
 */
@Entity
@Getter
@Setter
public class Consultorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero;

    private int piso;

}
