package com.medicalcms.anamneses.handlers;

import com.medicalcms.Validable;
import lombok.Data;


@Data
class EditAnamnesePayload implements Validable {
    String drugs;
    String orders;

    public boolean isValid() {
        return true;
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
}
