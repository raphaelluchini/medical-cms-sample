package com.medicalcms.patients.handlers;

import com.medicalcms.requestsHandlers.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.EmptyPayload;
import com.medicalcms.patients.PatientModel;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static j2html.TagCreator.*;

public class IndexPatientsHandler extends AbstractRequestHandler<EmptyPayload> {
    public PatientModel model;

    public IndexPatientsHandler(PatientModel model) {
        super(EmptyPayload.class);
        this.model = model;
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map urlParams, boolean shouldReturnHtml) {
        if (shouldReturnHtml) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data", model.getAll());
            String html = toHandlebars(new ModelAndView(map, "patients/index.hbs"));
            return Answer.ok(html);
        } else {
            String json = dataToJson(model.getAll());
            return Answer.ok(json);
        }
    }

}
