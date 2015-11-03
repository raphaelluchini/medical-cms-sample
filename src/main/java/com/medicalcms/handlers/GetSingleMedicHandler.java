package com.medicalcms.handlers;

import com.medicalcms.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.medics.Medic;
import com.medicalcms.medics.MedicModel;
import com.medicalcms.medics.MedicSql2oModel;

import java.util.Map;


public class GetSingleMedicHandler extends AbstractRequestHandler<EmptyPayload> {
    public MedicModel model;

    public GetSingleMedicHandler(MedicSql2oModel model) {
        super(EmptyPayload.class);
        this.model = model;
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String,String> urlParams, boolean shouldReturnHtml) {
        if (!urlParams.containsKey(":id")) {
            throw new IllegalArgumentException();
        }
        int id;
        try {
            id =Integer.parseInt(urlParams.get(":id"));
        } catch (IllegalArgumentException e) {
            return new Answer(404);
        }

        Medic medic = model.get(id);
        if (medic != null) {
            return new Answer(404);
        }
        return Answer.ok(dataToJson(medic));
    }

}
