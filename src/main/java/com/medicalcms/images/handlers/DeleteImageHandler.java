package com.medicalcms.images.handlers;

import com.medicalcms.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.handlers.EmptyPayload;
import com.medicalcms.images.Image;
import com.medicalcms.images.ImageModel;

import java.util.Map;
import java.util.Optional;

public class DeleteImageHandler extends AbstractRequestHandler<EmptyPayload> {

    private ImageModel model;

    public DeleteImageHandler(ImageModel model) {
        super(EmptyPayload.class);
        this.model = model;
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
        if (!urlParams.containsKey(":id")) {
            throw new IllegalArgumentException();
        }
        int id;
        try {
            id = Integer.parseInt(urlParams.get(":id"));
        } catch (IllegalArgumentException e) {
            return new Answer(404);
        }

        Optional<Image> image = model.get(id);
        if (!image.isPresent()) {
            return new Answer(404);
        }
        model.delete(id);
        return new Answer(200);
    }
}
