package com.medicalcms.anamneses.handlers;

import com.medicalcms.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.anamneses.AnamneseModel;

import java.util.Map;

public class CreateAnamneseHandler extends AbstractRequestHandler<CreateAnamnesePayload> {

    private AnamneseModel model;

    public CreateAnamneseHandler(AnamneseModel model) {
        super(CreateAnamnesePayload.class);
        this.model = model;
    }

    @Override
    protected Answer processImpl(CreateAnamnesePayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
        Long id = model.create(value.getDrugs(), value.getOrders(), value.getDate(), value.getMedic_id(), value.getPatient_id());
        return new Answer(201, Long.toString(id));
    }
}
