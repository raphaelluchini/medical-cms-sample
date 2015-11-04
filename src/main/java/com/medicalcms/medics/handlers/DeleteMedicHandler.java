package com.medicalcms.medics.handlers;

import com.medicalcms.requestsHandlers.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.EmptyPayload;
import com.medicalcms.medics.Medic;
import com.medicalcms.medics.MedicModel;

import java.util.Map;
import java.util.Optional;

public class DeleteMedicHandler extends AbstractRequestHandler<EmptyPayload> {

    private MedicModel model;

    public DeleteMedicHandler(MedicModel model) {
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

        Optional<Medic> medic = model.get(id);
        if (!medic.isPresent()) {
            return new Answer(404);
        }
        model.delete(id);
        return new Answer(200);
    }
}
