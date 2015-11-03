package com.medicalcms.anamneses;

import java.util.Date;
import java.util.List;

public interface AnamneseModel {
    int create(String drugs, String orders, Date date, int medicId, int patientId);
    List<Anamnese> getAll();
    Anamnese get(int anamneseId);
    void update(Anamnese anamnese);
    void delete(int anamneseId);
}