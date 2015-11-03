package com.medicalcms.medics;

import com.medicalcms.anamneses.Anamnese;

import java.util.List;

public interface MedicModel {
    int create(String name);
    List<Medic> getAll();
    Medic get(int medicId);
    List<Anamnese> getAllAnamneseOn(int medicId);
    void update(Medic medic);
    void delete(int patientId);
}