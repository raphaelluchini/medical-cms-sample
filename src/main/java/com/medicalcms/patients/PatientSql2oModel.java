package com.medicalcms.patients;

import com.medicalcms.anamneses.Anamnese;
import com.medicalcms.medics.Medic;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.Optional;

public class PatientSql2oModel implements PatientModel {

    private Sql2o sql2o;

    public PatientSql2oModel(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Long create(String name, int age, String email, String city, String gender) {
        try (Connection conn = sql2o.beginTransaction()) {
            Long patientId = (Long) conn.createQuery("INSERT INTO patients(name, age, email, city, gender) VALUES (:name, :age, :email, :city, :gender)")
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
    public Optional<Patient> get(int patientId) {
        try (Connection conn = sql2o.open()) {
            List<Patient> patients = conn.createQuery("SELECT * FROM patients WHERE id=:patientId")
                    .addParameter("patientId", patientId)
                    .executeAndFetch(Patient.class);
            if (patients.size() == 0) {
                return Optional.empty();
            } else if (patients.size() == 1) {
                return Optional.of(patients.get(0));
            } else {
                throw new RuntimeException();
            }
        }
    }

    @Override
    public List<Anamnese> getAllAnamneseOn(int patientId) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM anamneses WHERE patients_id=:patientId ORDER BY date DESC")
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
