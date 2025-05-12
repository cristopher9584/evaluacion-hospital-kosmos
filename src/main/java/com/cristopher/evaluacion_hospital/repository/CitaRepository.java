package com.cristopher.evaluacion_hospital.repository;

import com.cristopher.evaluacion_hospital.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio de acceso a datos para la entidad {@link Cita}.
 *
 * Permite realizar operaciones CRUD y consultas personalizadas sobre las citas.
 */
public interface CitaRepository extends JpaRepository<Cita, Long> {

    @Query("""
    SELECT c FROM Cita c
    WHERE (:fechaInicio IS NULL OR c.fecha >= :fechaInicio)
    AND (:fechaFin IS NULL OR c.fecha <= :fechaFin)
    AND (:doctor IS NULL OR LOWER(CONCAT(c.doctor.nombre, ' ', c.doctor.apellidoPaterno)) LIKE LOWER(CONCAT('%', :doctor, '%')))
    AND (:consultorio IS NULL OR CAST(c.consultorio.numero AS string) LIKE CONCAT('%', :consultorio, '%'))
""")
    List<Cita> buscarPorFiltros(
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin,
            @Param("doctor") String doctor,
            @Param("consultorio") String consultorio
    );



    boolean existsByConsultorioIdAndFecha(Long consultorioId, LocalDateTime fecha);

    boolean existsByDoctorIdAndFecha(Long doctorId, LocalDateTime fecha);

    long countByDoctorIdAndFechaBetween(Long doctorId, LocalDateTime inicio, LocalDateTime fin);

    List<Cita> findByPacienteAndFechaBetween(String paciente, LocalDateTime inicio, LocalDateTime fin);

}
