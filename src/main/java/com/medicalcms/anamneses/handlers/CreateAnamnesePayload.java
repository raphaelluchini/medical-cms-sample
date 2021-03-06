package com.medicalcms.anamneses.handlers;

import com.medicalcms.Validable;
import lombok.Data;

import java.util.Date;

@Data
class CreateAnamnesePayload implements Validable {
    String drugs;
    String orders;
    Date date;
    int medic_id;
    int patient_id;
    String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public boolean isValid() {
        return  date != null;
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

    public int getMedic_id() {
        return medic_id;
    }

    public void setMedic_id(int medic_id) {
        this.medic_id = medic_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }
}
