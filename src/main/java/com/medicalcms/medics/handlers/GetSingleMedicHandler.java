package com.medicalcms.medics.handlers;

import com.medicalcms.requestsHandlers.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.EmptyPayload;
import com.medicalcms.medics.Medic;
import com.medicalcms.medics.MedicModel;
import com.medicalcms.medics.MedicSql2oModel;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


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
            id = Integer.parseInt(urlParams.get(":id"));
        } catch (IllegalArgumentException e) {
            return new Answer(404);
        }

        Optional<Medic> medic = model.get(id);

        if (medic == null) {
            return new Answer(404);
        }

        if (shouldReturnHtml) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data", medic.get());
            String html = toHandlebars(new ModelAndView(map, "medics/edit.hbs"));
            return Answer.ok(html);
        } else {
            String json = dataToJson(medic.get());
            return Answer.ok(json);
        }
    }

}
