package com.medicalcms.anamneses;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Anamnese {
    int id;
    String drugs;
    String orders;
    Date date;
    int medics_id;
    int patients_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMedics_id() {
        return medics_id;
    }

    public void setMedics_id(int medics_id) {
        this.medics_id = medics_id;
    }

    public int getPatients_id() {
        return patients_id;
    }

    public void setPatients_id(int patients_id) {
        this.patients_id = patients_id;
    }
}
