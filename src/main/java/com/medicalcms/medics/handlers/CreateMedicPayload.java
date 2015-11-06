package com.medicalcms.medics.handlers;

import com.medicalcms.Validable;
import lombok.Data;

@Data
class CreateMedicPayload implements Validable {
    private String name;

    public boolean isValid() {
        if(name != null){
            if(name.isEmpty()){
                return false;
            }
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
