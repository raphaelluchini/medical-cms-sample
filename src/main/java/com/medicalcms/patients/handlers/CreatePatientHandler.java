package com.medicalcms.patients.handlers;

import com.medicalcms.requestsHandlers.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.patients.PatientModel;

import java.util.Map;

public class CreatePatientHandler extends AbstractRequestHandler<CreatePatientPayload> {

    private PatientModel model;

    public CreatePatientHandler(PatientModel model) {
        super(CreatePatientPayload.class);
        this.model = model;
    }

    @Override
    protected Answer processImpl(CreatePatientPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
        Long id = model.create(value.getName(), value.getAge(), value.getEmail(), value.getCity(), value.getGender());
        return new Answer(201, Long.toString(id));
    }
}
