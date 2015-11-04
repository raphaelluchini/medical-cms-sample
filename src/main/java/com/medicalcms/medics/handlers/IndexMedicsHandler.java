package com.medicalcms.medics.handlers;

import com.medicalcms.requestsHandlers.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.EmptyPayload;
import com.medicalcms.medics.MedicModel;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class IndexMedicsHandler extends AbstractRequestHandler<EmptyPayload> {
    public MedicModel model;
    public IndexMedicsHandler(MedicModel model) {
        super(EmptyPayload.class);
        this.model = model;
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map urlParams, boolean shouldReturnHtml) {
        if (shouldReturnHtml) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data", model.getAll());
            String html = toHandlebars(new ModelAndView(map, "medics/index.hbs"));
            return Answer.ok(html);
        } else {
            String json = dataToJson(model.getAll());
            return Answer.ok(json);
        }
    }

}
