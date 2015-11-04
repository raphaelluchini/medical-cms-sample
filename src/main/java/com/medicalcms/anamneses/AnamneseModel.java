package com.medicalcms.anamneses;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AnamneseModel {
    Long create(String drugs, String orders, Date date, int medicId, int patientId);
    List<Anamnese> getAll();
    Optional<Anamnese> get(int anamneseId);
    void update(Anamnese anamnese);
    void delete(int anamneseId);
}