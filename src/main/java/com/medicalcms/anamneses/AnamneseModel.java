package com.medicalcms.anamneses;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AnamneseModel {
    Long create(String drugs, String orders, Date date, int medicId, int patientId, String src);
    List<Anamnese> getAll();
    List<Anamnese> getByDates(int patientId, String start, String end);
    Optional<Anamnese> get(int anamneseId);
    void update(Anamnese anamnese);
    void delete(int anamneseId);
}