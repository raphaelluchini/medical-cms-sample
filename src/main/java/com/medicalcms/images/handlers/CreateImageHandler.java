package com.medicalcms.images.handlers;

import com.medicalcms.Answer;
import com.medicalcms.requestsHandlers.MultipartRequestHandler;
import com.medicalcms.images.ImageModel;

import java.util.Map;

public class CreateImageHandler extends MultipartRequestHandler<CreateImagePayload> {

    private ImageModel model;

    public CreateImageHandler(ImageModel model) {
        super(CreateImagePayload.class);
        this.model = model;
    }

    @Override
    protected Answer processImpl(CreateImagePayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
        Long id = model.create(value.getSrc(), value.getAnamnese_id());
        return new Answer(201, Long.toString(id));
    }
}
