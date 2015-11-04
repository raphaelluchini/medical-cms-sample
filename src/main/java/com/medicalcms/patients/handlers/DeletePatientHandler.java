package com.medicalcms.patients.handlers;

import com.medicalcms.requestsHandlers.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.EmptyPayload;
import com.medicalcms.patients.Patient;
import com.medicalcms.patients.PatientModel;

import java.util.Map;
import java.util.Optional;

public class DeletePatientHandler extends AbstractRequestHandler<EmptyPayload> {

    private PatientModel model;

    public DeletePatientHandler(PatientModel model) {
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

        Optional<Patient> patient = model.get(id);
        if (!patient.isPresent()) {
            return new Answer(404);
        }
        model.delete(id);
        return new Answer(200);
    }
}
