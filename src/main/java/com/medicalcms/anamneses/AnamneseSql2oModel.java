package com.medicalcms.anamneses;

import com.medicalcms.medics.Medic;
import com.medicalcms.medics.MedicModel;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Date;
import java.util.List;

public class AnamneseSql2oModel implements AnamneseModel {

    private Sql2o sql2o;

    public AnamneseSql2oModel(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public int create(String drugs, String orders, Date date, int medicId, int patientId) {
        try (Connection conn = sql2o.beginTransaction()) {
            int anamneseId = (int) conn.createQuery("INSERT INTO anamneses(drugs,orders,date,medic_id,patient_id) VALUES (:drugs,:orders,:date,:medicId,:patientId)")
                    .addParameter("drugs", drugs)
                    .addParameter("orders", orders)
                    .addParameter("date", date)
                    .addParameter("medicId", medicId)
                    .addParameter("patientId", patientId)
                    .executeUpdate()
                    .getKey();
            conn.commit();
            return anamneseId;
        }
    }

    @Override
    public List<Anamnese> getAll() {
        try (Connection conn = sql2o.open()) {
            List<Anamnese> anamneses = conn.createQuery("SELECT * FROM anamneses")
                    .executeAndFetch(Anamnese.class);
            return anamneses;
        }
    }

    @Override
    public Anamnese get(int anamneseId) {
        try (Connection conn = sql2o.open()) {
            Anamnese anamnese = conn.createQuery("SELECT * FROM anamneses WHERE id=:")
                    .addParameter("anamneseId", anamneseId)
                    .executeAndFetchFirst(Anamnese.class);
            return anamnese;
        }
    }

    @Override
    public void update(Anamnese anamnese) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("UPDATE anamneses SET drugs=:drugs, orders=:orders, date=:date, medic_id=:medicId, patient_id=:patientId WHERE id=:anamneseId")
                    .addParameter("anamneseId", anamnese.getId())
                    .addParameter("drugs", anamnese.getDrugs())
                    .addParameter("orders", anamnese.getOrders())
                    .addParameter("date", anamnese.getDate())
                    .addParameter("medicId", anamnese.getMedic_id())
                    .addParameter("patientId", anamnese.getPatient_id())
                    .executeUpdate();
        }
    }

    @Override
    public void delete(int anamneseId) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM anamneses WHERE id=:anamneseId")
                    .addParameter("anamneseId", anamneseId)
                    .executeUpdate();
        }
    }
}
