package com.medicalcms.medics;

import com.medicalcms.Validable;
import lombok.Data;


@Data
class EditMedicPayload implements Validable {
    private String name;

    public boolean isValid() {
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
