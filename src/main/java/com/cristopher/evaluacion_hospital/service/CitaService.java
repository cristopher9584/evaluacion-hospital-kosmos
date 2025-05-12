package com.cristopher.evaluacion_hospital.service;

import com.cristopher.evaluacion_hospital.entity.Cita;
import com.cristopher.evaluacion_hospital.entity.Consultorio;
import com.cristopher.evaluacion_hospital.repository.CitaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Servicio que contiene la lógica de negocio relacionada con las citas médicas.
 *
 * Se encarga de gestionar la creación, consulta y filtrado de citas.
 */
@Service
@AllArgsConstructor
public class CitaService {

    private final CitaRepository repository;

    /**
     * Guarda una nueva cita en el sistema después de realizar las siguientes validaciones:
     * <ul>
     *     <li>No se puede agendar una cita en el mismo consultorio a la misma hora.</li>
     *     <li>No se puede agendar una cita para el mismo doctor a la misma hora.</li>
     *     <li>No se puede agendar una cita para el mismo paciente a la misma hora ni con menos de 2 horas de diferencia.</li>
     *     <li>El doctor no puede tener más de 8 citas programadas en un mismo día.</li>
     * </ul>
     *
     * Si cualquiera de estas validaciones falla, se lanzará una excepción {@link IllegalArgumentException}.
     * Si todas las validaciones pasan, la cita será guardada en la base de datos.
     *
     * @param cita La cita que se desea guardar en el sistema.
     * @return La cita guardada, si todas las validaciones son satisfactorias.
     * @throws IllegalArgumentException Si alguna de las validaciones falla:
     *         <ul>
     *             <li>Si el consultorio ya tiene una cita a la misma hora.</li>
     *             <li>Si el doctor ya tiene una cita a la misma hora.</li>
     *             <li>Si el paciente ya tiene una cita a la misma hora o con menos de 2 horas de diferencia.</li>
     *             <li>Si el doctor tiene 8 citas programadas en el mismo día.</li>
     *         </ul>
     */
    public Cita guardar(Cita cita) {

        //No se puede agendar cita en un mismo consultorio a la misma hora
        boolean consultorioOcupado = repository.existsByConsultorioIdAndFecha(
                cita.getConsultorio().getId(),
                cita.getFecha()
        );
        if (consultorioOcupado) {
            throw new IllegalArgumentException("Ya hay una cita en ese consultorio a esa hora.");
        }

        //No se puede agendar cita para un mismo Dr. a la misma hora
        boolean doctorOcupado = repository.existsByDoctorIdAndFecha(
                cita.getDoctor().getId(),
                cita.getFecha()
        );
        if (doctorOcupado) {
            throw new IllegalArgumentException("El doctor ya tiene una cita a esa hora.");
        }


        /* No se puede agendar cita para un paciente a la una misma hora ni con menos de 2 horas
        de diferencia para el mismo día.*/
        // Obtener las citas previas del paciente para el mismo día
        LocalDateTime fechaConsulta = cita.getFecha();
        LocalDateTime inicioDelDia = fechaConsulta.toLocalDate().atStartOfDay();
        LocalDateTime finDelDia = fechaConsulta.toLocalDate().atTime(LocalTime.MAX);

        List<Cita> citasDia = repository.findByPacienteAndFechaBetween(cita.getPaciente(), inicioDelDia, finDelDia);

        for (Cita citaExistente : citasDia) {
            LocalDateTime inicioExistente = citaExistente.getFecha();

            // Rango prohibido: desde 2 horas antes hasta 3 horas después de la nueva cita (3 horas suponiendo una duracion de 1 hora de todas las citas)
            LocalDateTime nuevaCita = cita.getFecha();
            LocalDateTime inicioRango = nuevaCita.minusHours(2);
            LocalDateTime finRango = nuevaCita.plusHours(3);

            if (!inicioExistente.isBefore(inicioRango) && !inicioExistente.isAfter(finRango)) {
                throw new IllegalArgumentException("La nueva cita debe estar al menos 2 horas de distancia de otra cita del mismo paciente.");
            }
        }


        //Un mismo doctor no puede tener más de 8 citas en el día.
        long citasDelDia = repository.countByDoctorIdAndFechaBetween(cita.getDoctor().getId(), inicioDelDia, finDelDia);


        if (citasDelDia >= 8) {
            throw new IllegalArgumentException("El doctor ya tiene 8 citas agendadas para este día.");
        }

        //se guarda la cita si pasa todas las restricciones.
        return repository.save(cita);
    }

    /**
     * Busca las citas que coinciden con los filtros proporcionados de fecha, doctor y consultorio.
     *
     * @param fecha La fecha y hora para filtrar las citas.
     * @param doctor El nombre del doctor para filtrar las citas.
     * @param consultorio El número o identificador del consultorio para filtrar las citas.
     * @return Una lista de citas que coinciden con los filtros proporcionados. Si no hay coincidencias, la lista estará vacía.
     */
    public List<Cita> buscarCitas(LocalDate fecha, String doctor, String consultorio) {
        LocalDateTime inicio = null;
        LocalDateTime fin = null;

        if (fecha != null) {
            inicio = fecha.atStartOfDay();
            fin = fecha.atTime(LocalTime.MAX);
        }

        if (doctor != null && doctor.isBlank()) doctor = null;
        if (consultorio != null && consultorio.isBlank()) consultorio = null;

        return repository.buscarPorFiltros(inicio, fin, doctor, consultorio);
    }

    /**
     * Elimina una cita con el ID proporcionado, si su fecha y hora son anteriores a la fecha y hora actual.
     * Si la cita está programada para el futuro, no se eliminará y se mostrará un mensaje de error.
     *
     * @param id El ID de la cita que se desea eliminar.
     * @throws IllegalArgumentException Si la cita está programada para el futuro y no se puede eliminar.
     */
    public void eliminar(Long id) {
        Optional<Cita> citaOpt = repository.findById(id);

        if (citaOpt.isEmpty()) {
            throw new IllegalArgumentException("La cita no existe.");
        }

        Cita cita = citaOpt.get();
        LocalDateTime ahora = LocalDateTime.now();

        // Solo se elimina si la fecha de la cita es futura
        if (cita.getFecha().isAfter(ahora)) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se puede cancelar una cita que ya ha pasado.");
        }
    }


}
