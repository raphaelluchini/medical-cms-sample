package com.medicalcms.patients;


import com.medicalcms.anamneses.Anamnese;

import java.util.List;
import java.util.Optional;

public interface PatientModel {
    Long create(String name, int age, String email, String city, String gender);
    List<Patient> getAll();
    Optional<Patient> get(int patientId);
    List<Anamnese> getAllAnamneseOn(int patientId);
    void update(Patient patient);
    void delete(int patientId);
}
