package com.medicalcms.anamneses.handlers;

import com.medicalcms.medics.Medic;
import com.medicalcms.medics.MedicModel;
import com.medicalcms.patients.Patient;
import com.medicalcms.patients.PatientModel;
import com.medicalcms.requestsHandlers.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.anamneses.Anamnese;
import com.medicalcms.anamneses.AnamneseModel;
import com.medicalcms.EmptyPayload;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class GetSingleAnamneseHandler extends AbstractRequestHandler<EmptyPayload> {
    public AnamneseModel model;
    public MedicModel medicModel;
    public PatientModel patientModel;

    public GetSingleAnamneseHandler(AnamneseModel model, MedicModel medicModel, PatientModel patientModel) {
        super(EmptyPayload.class);
        this.model = model;
        this.medicModel = medicModel;
        this.patientModel = patientModel;
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

        Optional<Anamnese> anamnese = model.get(id);

        if (anamnese == null) {
            return new Answer(404);
        }

        Optional<Medic> medic = medicModel.get(anamnese.get().getMedics_id());
        Optional<Patient> patient = patientModel.get(anamnese.get().getPatients_id());

        if (shouldReturnHtml) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data", anamnese.get());
            map.put("medic", medic.get());
            map.put("patient", patient.get());
            String html = toHandlebars(new ModelAndView(map, "anamneses/edit.hbs"));
            return Answer.ok(html);
        } else {
            String json = dataToJson(anamnese.get());
            return Answer.ok(json);
        }
    }

}
