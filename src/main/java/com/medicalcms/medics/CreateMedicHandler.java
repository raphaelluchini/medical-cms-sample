package com.medicalcms.medics;

import com.medicalcms.AbstractRequestHandler;
import com.medicalcms.Answer;

import java.util.Map;

public class CreateMedicHandler extends AbstractRequestHandler<CreateMedicPayload> {

    private MedicSql2oModel model;

    public CreateMedicHandler(MedicSql2oModel model) {
        super(CreateMedicPayload.class);
        this.model = model;
    }

    @Override
    protected Answer processImpl(CreateMedicPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
        Long id = model.create(value.getName());
        System.out.println(id);
        return new Answer(201, Long.toString(id));
    }
}
