package com.medicalcms.anamneses.handlers;

import com.medicalcms.requestsHandlers.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.anamneses.Anamnese;
import com.medicalcms.anamneses.AnamneseModel;
import com.medicalcms.medics.Medic;
import com.medicalcms.medics.MedicModel;
import com.medicalcms.patients.Patient;
import com.medicalcms.patients.PatientModel;

import java.util.Map;
import java.util.Optional;

public class EditAnamneseHandler extends AbstractRequestHandler<EditAnamnesePayload> {

    private AnamneseModel model;

    public EditAnamneseHandler(AnamneseModel model) {
        super(EditAnamnesePayload.class);
        this.model = model;
    }

    @Override
    protected Answer processImpl(EditAnamnesePayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
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

        if (value.getDrugs() != null) {
            anamnese.get().setDrugs(value.getDrugs());
        }
        if (value.getOrders() != null) {
            anamnese.get().setOrders(value.getOrders());
        }


        model.update(anamnese.get());
        return new Answer(200);
    }
}
