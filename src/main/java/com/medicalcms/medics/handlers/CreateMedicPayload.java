package com.medicalcms.medics.handlers;

import com.medicalcms.Validable;
import lombok.Data;

@Data
class CreateMedicPayload implements Validable {
    private String name;

    public boolean isValid() {
        return name != null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
