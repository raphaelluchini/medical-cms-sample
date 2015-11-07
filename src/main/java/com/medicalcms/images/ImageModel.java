package com.medicalcms.images;


import java.util.List;
import java.util.Optional;

public interface ImageModel {
    Long create(String name, Long anamneseId);
    List<Image> getAll();
    List<Image> getAllFrom(Long anamneseId);
    Optional<Image> get(Long imageId);
    void update(Image image);
    void delete(Long imageId);
}