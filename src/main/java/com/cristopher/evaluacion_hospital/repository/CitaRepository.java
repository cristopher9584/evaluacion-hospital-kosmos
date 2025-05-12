package com.cristopher.evaluacion_hospital.repository;

import com.cristopher.evaluacion_hospital.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    @Query("""
        SELECT c FROM Cita c
        WHERE (:fecha IS NULL OR c.fecha = :fecha)
        AND (:doctor IS NULL OR LOWER(c.doctor) LIKE LOWER(CONCAT('%', :doctor, '%')))
        AND (:consultorio IS NULL OR LOWER(c.consultorio) LIKE LOWER(CONCAT('%', :consultorio, '%')))
    """)
    List<Cita> buscarPorFiltros(@Param("fecha") LocalDateTime fecha,
                                @Param("doctor") String doctor,
                                @Param("consultorio") String consultorio);

    boolean existsByConsultorioIdAndFecha(Long consultorioId, LocalDateTime fecha);

    boolean existsByDoctorIdAndFecha(Long doctorId, LocalDateTime fecha);

    long countByDoctorIdAndFecha(Long doctorId, LocalDateTime fechaConsulta);

    List<Cita> findByPacienteAndFecha(String paciente, LocalDateTime fecha);

}
