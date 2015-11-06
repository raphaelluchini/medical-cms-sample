package com.medicalcms.images;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.Optional;

public class ImageSql2OModel implements ImageModel {

    private Sql2o sql2o;

    public ImageSql2OModel(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Long create(String src, int anamneseId) {
        try (Connection conn =  sql2o.open()) {
            Long patientId = (Long) conn.createQuery("INSERT INTO images(src,anamnese_id) VALUES (:src,:anamneseId)")
                    .addParameter("src", src)
                    .addParameter("anamneseId", anamneseId)
                    .executeUpdate()
                    .getKey();
            return patientId;
        }
    }

    @Override
      public List<Image> getAll() {
        try (Connection conn = sql2o.open()) {
            List<Image> images = conn.createQuery("SELECT * FROM images")
                    .executeAndFetch(Image.class);
            return images;
        }
    }

    @Override
    public List<Image> getAllFrom(int anamneseId) {
        try (Connection conn = sql2o.open()) {
            List<Image> images = conn.createQuery("SELECT * FROM images WHERE anamnese_id=:anamneseId")
                    .addParameter("anamneseId", anamneseId)
                    .executeAndFetch(Image.class);
            return images;
        }
    }

    @Override
    public Optional<Image> get(int imageId) {
        try (Connection conn = sql2o.open()) {
            List<Image> images = conn.createQuery("SELECT * FROM images WHERE id=:imageId")
                    .addParameter("imageId", imageId)
                    .executeAndFetch(Image.class);
            if (images.size() == 0) {
                return Optional.empty();
            } else if (images.size() == 1) {
                return Optional.of(images.get(0));
            } else {
                throw new RuntimeException();
            }
        }
    }

    @Override
    public void update(Image image) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("UPDATE images SET src=:src WHERE id=:id")
                    .addParameter("id", image.getId())
                    .addParameter("src", image.getSrc())
                    .executeUpdate();
        }
    }

    @Override
    public void delete(int imageId) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM images WHERE id=:imageId")
                    .addParameter("imageId", imageId)
                    .executeUpdate();
        }
    }

}
