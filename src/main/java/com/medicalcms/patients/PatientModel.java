package com.medicalcms.patients;


import com.medicalcms.anamneses.Anamnese;

import java.util.List;

public interface PatientModel {
    int create(String name, int age, String email, String city, String gender);
    List<Patient> getAll();
    Patient get(int patientId);
    List<Anamnese> getAllAnamneseOn(int patientId);
    void update(Patient patient);
    void delete(int patientId);
}
