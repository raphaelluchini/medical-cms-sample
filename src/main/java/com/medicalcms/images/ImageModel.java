package com.medicalcms.images;


import java.util.List;
import java.util.Optional;

public interface ImageModel {
    Long create(String name, int anamneseId);
    List<Image> getAll();
    Optional<Image> get(int imageId);
    void update(Image image);
    void delete(int imageId);
}