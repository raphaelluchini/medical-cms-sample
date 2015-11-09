package com.medicalcms.anamneses;

import com.medicalcms.images.ImageSql2OModel;
import com.medicalcms.medics.Medic;
import com.medicalcms.medics.MedicModel;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AnamneseSql2oModel implements AnamneseModel {

    private Sql2o sql2o;

    public AnamneseSql2oModel(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Long create(String drugs, String orders, Date date, int medicId, int patientId, String src) {
        try (Connection conn = sql2o.beginTransaction()) {
            Long anamneseId = (Long) conn.createQuery("INSERT INTO anamneses(drugs,orders,date,medics_id,patients_id) VALUES (:drugs,:orders,:date,:medicId,:patientId)")
                    .addParameter("drugs", drugs)
                    .addParameter("orders", orders)
                    .addParameter("date", date)
                    .addParameter("medicId", medicId)
                    .addParameter("patientId", patientId)
                    .executeUpdate()
                    .getKey();
            conn.commit();
            if(src != null && !src.isEmpty()){
                new ImageSql2OModel(sql2o).create(src, anamneseId);
            }
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
    public Optional<Anamnese> get(int anamneseId) {
        try (Connection conn = sql2o.open()) {
            List<Anamnese>  anamnese = conn.createQuery("SELECT * FROM anamneses WHERE id=:anamneseId")
                    .addParameter("anamneseId", anamneseId)
                    .executeAndFetch(Anamnese.class);
            if (anamnese.size() == 0) {
                return Optional.empty();
            } else if (anamnese.size() == 1) {
                return Optional.of(anamnese.get(0));
            } else {
                throw new RuntimeException();
            }
        }
    }

    @Override
    public void update(Anamnese anamnese) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("UPDATE anamneses SET drugs=:drugs, orders=:orders, date=:date, medics_id=:medicId, patients_id=:patientId WHERE id=:anamneseId")
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
