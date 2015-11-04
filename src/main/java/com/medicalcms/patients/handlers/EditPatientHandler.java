package com.medicalcms.patients.handlers;

import com.medicalcms.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.patients.Patient;
import com.medicalcms.patients.PatientModel;

import java.util.Map;
import java.util.Optional;

public class EditPatientHandler extends AbstractRequestHandler<EditPatientPayload> {

    private PatientModel model;

    public EditPatientHandler(PatientModel model) {
        super(EditPatientPayload.class);
        this.model = model;
    }

    @Override
    protected Answer processImpl(EditPatientPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
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
        if (value.getName() != null) {
            patient.get().setName(value.getName());
        }
        if (value.getAge() != 0) {
            patient.get().setAge(value.getAge());
        }
        if (value.getEmail() != null) {
            patient.get().setEmail(value.getEmail());
        }
        if (value.getGender() != null) {
            patient.get().setGender(value.getGender());
        }
        if (value.getCity() != null) {
            patient.get().setCity(value.getCity());
        }
        model.update(patient.get());
        return new Answer(200);
    }
}
