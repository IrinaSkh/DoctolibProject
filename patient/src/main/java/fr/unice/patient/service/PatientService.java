package fr.unice.patient.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import fr.unice.patient.model.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PatientService {

    private final List<Patient> patients = new ArrayList<>();

    public PatientService() {
        // Instanciation de quelques patients au démarrage
        patients.add(new Patient(1L, "Martin", "alice.martin@example.com"));
        patients.add(new Patient(2L, "Dupont", "bob.dupont@example.com"));
        patients.add(new Patient(3L, "Lemoine", "charlie.lemoine@example.com"));
    }

    @HystrixCommand(fallbackMethod = "fallbackGetAllPatients")
    public List<Patient> getAllPatients() {

        return new ArrayList<>(patients);
    }

    public List<Patient> fallbackGetAllPatients() {
        log.warn("Fallback pour getAllPatients - Service indisponible.");
        return new ArrayList<>();
    }

    @HystrixCommand(fallbackMethod = "fallbackGetPatientById")
    public Patient getPatientById(Long id) {
        return patients.stream()
                .filter(patient -> patient.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Patient non trouvé"));
    }

    public Patient fallbackGetPatientById(Long id) {
        log.warn("Fallback pour getPatientById avec ID: {}", id);
        return null;
    }

    @HystrixCommand(fallbackMethod = "fallbackSavePatient")
    public Patient savePatient(Patient patient) {
        patients.add(patient);
        return patient;
    }

    public Patient fallbackSavePatient(Patient patient) {
        log.warn("Fallback pour savePatient");
        return null;
    }

    @HystrixCommand(fallbackMethod = "fallbackDeletePatient")
    public void deletePatient(Long id) {
        patients.removeIf(patient -> patient.getId().equals(id));
    }

    public void fallbackDeletePatient(Long id) {
        log.warn("Fallback pour deletePatient avec ID: {}", id);
    }
}
