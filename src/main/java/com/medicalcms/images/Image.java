package com.medicalcms.images;

import lombok.Data;

@Data
public class Image {
    int id;
    String src;
    int anamneses_id;

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
        return anamneses_id;
    }

    public void setAnamnese_id(int anamneses_id) {
        this.anamneses_id = anamneses_id;
    }
}
