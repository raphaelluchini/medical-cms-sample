package com.medicalcms.anamneses.handlers;

import com.medicalcms.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.anamneses.Anamnese;
import com.medicalcms.anamneses.AnamneseModel;
import com.medicalcms.handlers.EmptyPayload;

import java.util.Map;
import java.util.Optional;

public class DeleteAnamneseHandler extends AbstractRequestHandler<EmptyPayload> {

    private AnamneseModel model;

    public DeleteAnamneseHandler(AnamneseModel model) {
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

        Optional<Anamnese> anamnese = model.get(id);
        if (!anamnese.isPresent()) {
            return new Answer(404);
        }
        model.delete(id);
        return new Answer(200);
    }
}
