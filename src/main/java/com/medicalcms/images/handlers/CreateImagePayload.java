package com.medicalcms.images.handlers;

import com.medicalcms.Validable;
import lombok.Data;

@Data
class CreateImagePayload implements Validable {
    private String src;
    private Long anamnese_id;

    public boolean isValid() {
        return src != null;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Long getAnamnese_id() {
        return anamnese_id;
    }

    public void setAnamnese_id(Long anamnese_id) {
        this.anamnese_id = anamnese_id;
    }
}
