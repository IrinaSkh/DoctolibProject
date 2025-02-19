    package fr.unice.patient.service;

    import fr.unice.patient.model.Patient;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;
    import java.util.List;

    @Service
    public class PatientService {

        private final List<Patient> patients = new ArrayList<>();

        public PatientService() {
            // Instanciation de quelques patients au démarrage
            patients.add(new Patient(1L, "Martin", "alice.martin@example.com"));
            patients.add(new Patient(2L,  "Dupont", "bob.dupont@example.com"));
            patients.add(new Patient(3L, "Lemoine", "charlie.lemoine@example.com"));
        }

        public List<Patient> getAllPatients() {
            return new ArrayList<>(patients);
        }

        public Patient getPatientById(Long id) {
            return patients.stream()
                    .filter(patient -> patient.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }

        public Patient savePatient(Patient patient) {
            patients.add(patient);
            return patient;
        }

        public void deletePatient(Long id) {
            patients.removeIf(patient -> patient.getId().equals(id));
        }
    }
