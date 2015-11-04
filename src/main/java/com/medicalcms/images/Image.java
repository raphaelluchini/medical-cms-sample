package com.medicalcms.images;

import lombok.Data;

@Data
public class Image {
    int id;
    String src;
    int anamnese_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getAnamnese_id() {
        return anamnese_id;
    }

    public void setAnamnese_id(int anamnese_id) {
        this.anamnese_id = anamnese_id;
    }
}
