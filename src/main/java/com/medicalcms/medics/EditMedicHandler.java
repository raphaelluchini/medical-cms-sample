package com.medicalcms.medics;

import com.medicalcms.AbstractRequestHandler;
import com.medicalcms.Answer;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class EditMedicHandler extends AbstractRequestHandler<EditMedicPayload> {

    private MedicSql2oModel model;

    public EditMedicHandler(MedicSql2oModel model) {
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
