package com.medicalcms.medics;

import com.medicalcms.anamneses.Anamnese;
import com.medicalcms.patients.Patient;
import com.medicalcms.patients.PatientModel;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class MedicSql2oModel implements MedicModel {

    private Sql2o sql2o;

    public MedicSql2oModel(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public int create(String name) {
        try (Connection conn = sql2o.beginTransaction()) {
            int patientId = (int) conn.createQuery("INSERT INTO medics(name) VALUES (:name)")
                    .addParameter("name", name)
                    .executeUpdate()
                    .getKey();
            conn.commit();
            return patientId;
        }
    }

    @Override
    public List<Medic> getAll() {
        try (Connection conn = sql2o.open()) {
            List<Medic> medics = conn.createQuery("SELECT * FROM medics")
                    .executeAndFetch(Medic.class);
            return medics;
        }
    }

    @Override
    public Medic get(int medicId) {
        try (Connection conn = sql2o.open()) {
            Medic medic = conn.createQuery("SELECT * FROM medics WHERE id=:medicId")
                    .addParameter("medicId", medicId)
                    .executeAndFetchFirst(Medic.class);
            return medic;
        }
    }

    @Override
    public List<Anamnese> getAllAnamneseOn(int medicId) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM anamneses WHERE medic_id=:medicId")
                    .addParameter("patientId", medicId)
                    .executeAndFetch(Anamnese.class);
        }
    }

    @Override
    public void update(Medic medic) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("UPDATE medics SET name=:name WHERE id=:medicId")
                    .addParameter("medicId", medic.getId())
                    .addParameter("name", medic.getName())
                    .executeUpdate();
        }
    }

    @Override
    public void delete(int medicId) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM medics WHERE id=:medicId")
                    .addParameter("medicId", medicId)
                    .executeUpdate();
        }
    }

}
