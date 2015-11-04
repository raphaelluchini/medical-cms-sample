package com.medicalcms.medics;

import com.medicalcms.anamneses.Anamnese;

import java.util.List;
import java.util.Optional;

public interface MedicModel {
    Long create(String name);
    List<Medic> getAll();
    Optional<Medic> get(int medicId);
    List<Anamnese> getAllAnamneseOn(int medicId);
    void update(Medic medic);
    void delete(int patientId);
}