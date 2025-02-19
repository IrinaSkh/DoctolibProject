package fr.unice.patient.controller;

import fr.unice.patient.model.Patient;
import fr.unice.patient.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/patients")
@Api(tags = "Patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @ApiOperation("Get all patients")
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @ApiOperation("Get a patient by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(
            @ApiParam("ID of the patient to retrieve") @PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @ApiOperation("Create a new patient")
    @PostMapping
    public ResponseEntity<Patient> createPatient(
            @ApiParam("Patient object to be created") @RequestBody Patient patient) {
        Patient createdPatient = patientService.savePatient(patient);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }

    @ApiOperation("Update an existing patient")
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(
            @ApiParam("ID of the patient to update") @PathVariable Long id,
            @ApiParam("Updated patient object") @RequestBody Patient updatedPatient) {
        Patient existingPatient = patientService.getPatientById(id);
        if (existingPatient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingPatient.setName(updatedPatient.getName());
        existingPatient.setEmail(updatedPatient.getEmail());
        patientService.savePatient(existingPatient);
        return new ResponseEntity<>(existingPatient, HttpStatus.OK);
    }

    @ApiOperation("Delete a patient")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(
            @ApiParam("ID of the patient to delete") @PathVariable Long id) {
        Patient existingPatient = patientService.getPatientById(id);
        if (existingPatient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

