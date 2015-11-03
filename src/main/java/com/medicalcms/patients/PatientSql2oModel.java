package com.medicalcms.patients;

import com.medicalcms.anamneses.Anamnese;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class PatientSql2oModel implements PatientModel {

    private Sql2o sql2o;

    public PatientSql2oModel(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public int create(String name, int age, String email, String city, String gender) {
        try (Connection conn = sql2o.beginTransaction()) {
            int patientId = (int) conn.createQuery("INSERT INTO patients(name, age, email, city, gender) VALUES (:name, :age, :email, :city, :gender)")
                    .addParameter("name", name)
                    .addParameter("age", age)
                    .addParameter("email", email)
                    .addParameter("city", city)
                    .addParameter("gender", gender)
                    .executeUpdate()
                    .getKey();
            conn.commit();
            return patientId;
        }
    }

    @Override
    public List<Patient> getAll() {
        try (Connection conn = sql2o.open()) {
            List<Patient> patients = conn.createQuery("SELECT * FROM patients")
                    .executeAndFetch(Patient.class);
            return patients;
        }
    }

    @Override
    public Patient get(int patientId) {
        try (Connection conn = sql2o.open()) {
            Patient patient = conn.createQuery("SELECT * FROM patients WHERE id=:patientId")
                    .addParameter("patientId", patientId)
                    .executeAndFetchFirst(Patient.class);
            return patient;
        }
    }

    @Override
    public List<Anamnese> getAllAnamneseOn(int patientId) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM anamneses WHERE patient_id=:patientId")
                    .addParameter("patientId", patientId)
                    .executeAndFetch(Anamnese.class);
        }
    }

    @Override
    public void update(Patient patient) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("UPDATE patients SET name=:name, age=:age,email=:email,gender=:gender,city=:city WHERE id=:patientId")
                    .addParameter("patientId", patient.getId())
                    .addParameter("name", patient.getName())
                    .addParameter("age", patient.getAge())
                    .addParameter("email", patient.getEmail())
                    .addParameter("gender", patient.getGender())
                    .addParameter("city", patient.getCity())
                    .executeUpdate();
        }
    }

    @Override
    public void delete(int patientId) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM patients WHERE id=:patientId")
                    .addParameter("patientId", patientId)
                    .executeUpdate();
        }
    }

}
