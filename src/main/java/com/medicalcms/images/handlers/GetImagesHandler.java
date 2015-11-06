package com.medicalcms.images.handlers;

import com.medicalcms.requestsHandlers.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.EmptyPayload;
import com.medicalcms.images.ImageModel;

import java.util.Map;
import java.util.stream.Collectors;

import static j2html.TagCreator.*;

public class GetImagesHandler extends AbstractRequestHandler<EmptyPayload> {
    public ImageModel model;
    public GetImagesHandler(ImageModel model) {
        super(EmptyPayload.class);
        this.model = model;
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
        if (!urlParams.containsKey(":anamnese_id")) {
            String json = dataToJson(model.getAll());
            return Answer.ok(json);
        }else{
            int id = Integer.parseInt(urlParams.get(":anamnese_id"));
            String json = dataToJson(model.getAllFrom(id));
            return Answer.ok(json);
        }
    }

}
