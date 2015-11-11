package com.medicalcms.anamneses.handlers;

import com.medicalcms.Answer;
import com.medicalcms.EmptyPayload;
import com.medicalcms.anamneses.AnamneseModel;
import com.medicalcms.requestsHandlers.AbstractRequestHandler;

import java.util.Map;

public class IndexAnamnseHandler extends AbstractRequestHandler<EmptyPayload> {
    public AnamneseModel model;

    public IndexAnamnseHandler(AnamneseModel model) {
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

        String json = dataToJson(
                model.getByDates(id,
                urlParams.get(":start"),
                urlParams.get(":end"))
        );
        return Answer.ok(json);


    }

}
