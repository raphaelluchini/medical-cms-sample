package com.medicalcms.medics.handlers;

import com.medicalcms.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.medics.Medic;
import com.medicalcms.medics.MedicModel;

import java.util.Map;
import java.util.Optional;

public class EditMedicHandler extends AbstractRequestHandler<EditMedicPayload> {

    private MedicModel model;

    public EditMedicHandler(MedicModel model) {
        super(EditMedicPayload.class);
        this.model = model;
    }

    @Override
    protected Answer processImpl(EditMedicPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
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
        if (value.getName() != null) {
            medic.get().setName(value.getName());
        }
        model.update(medic.get());
        return new Answer(200);
    }
}
