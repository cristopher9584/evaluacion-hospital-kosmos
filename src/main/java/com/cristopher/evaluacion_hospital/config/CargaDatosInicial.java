package com.cristopher.evaluacion_hospital.config;

import com.cristopher.evaluacion_hospital.entity.Doctor;
import com.cristopher.evaluacion_hospital.entity.Consultorio;
import com.cristopher.evaluacion_hospital.repository.DoctorRepository;
import com.cristopher.evaluacion_hospital.repository.ConsultorioRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CargaDatosInicial implements CommandLineRunner {

    private final DoctorRepository doctorRepo;
    private final ConsultorioRepository consultorioRepo;


    @Override
    public void run(String... args) {
        if (doctorRepo.count() == 0) {
            Doctor doc1 = new Doctor();
            doc1.setNombre("Laura");
            doc1.setApellidoPaterno("Hernández");
            doc1.setApellidoMaterno("Gómez");
            doc1.setEspecialidad("Pediatría");

            Doctor doc2 = new Doctor();
            doc2.setNombre("Carlos");
            doc2.setApellidoPaterno("Ramírez");
            doc2.setApellidoMaterno("Lopez");
            doc2.setEspecialidad("Cardiología");

            Doctor doc3 = new Doctor();
            doc3.setNombre("Cristopher");
            doc3.setApellidoPaterno("Urrutia");
            doc3.setApellidoMaterno("Jimenez");
            doc3.setEspecialidad("Podologia");

            doctorRepo.save(doc1);
            doctorRepo.save(doc2);
            doctorRepo.save(doc3);
        }

        if (consultorioRepo.count() == 0) {
            Consultorio con1 = new Consultorio();
            con1.setNumero(101);
            con1.setPiso(1);

            Consultorio con2 = new Consultorio();
            con2.setNumero(202);
            con2.setPiso(2);

            Consultorio con3 = new Consultorio();
            con3.setNumero(303);
            con3.setPiso(3);

            consultorioRepo.save(con1);
            consultorioRepo.save(con2);
            consultorioRepo.save(con3);
        }
    }
}
