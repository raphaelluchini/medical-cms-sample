package com.medicalcms.patients.handlers;

import com.medicalcms.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.handlers.EmptyPayload;
import com.medicalcms.medics.Medic;
import com.medicalcms.medics.MedicModel;
import com.medicalcms.medics.MedicSql2oModel;
import com.medicalcms.patients.Patient;
import com.medicalcms.patients.PatientModel;

import java.util.Map;
import java.util.Optional;

import static j2html.TagCreator.*;


public class GetSinglePatientHandler extends AbstractRequestHandler<EmptyPayload> {
    public PatientModel model;

    public GetSinglePatientHandler(PatientModel model) {
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
            id = Integer.parseInt(urlParams.get(":id"));
        } catch (IllegalArgumentException e) {
            return new Answer(404);
        }

        Optional<Patient> patient = model.get(id);

        if (patient == null) {
            return new Answer(404);
        }

        if (shouldReturnHtml) {
            String html = body().with(
                    h1("Patients"),
                    div().with(h2(patient.get().getName()))
            ).render();
            return Answer.ok(html);
        } else {
            String json = dataToJson(patient.get());
            return Answer.ok(json);
        }
    }

}
