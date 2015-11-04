package com.medicalcms.handlers;

import com.medicalcms.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.medics.Medic;
import com.medicalcms.medics.MedicModel;
import com.medicalcms.medics.MedicSql2oModel;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static j2html.TagCreator.*;


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
            String html = body().with(
                    h1("Medics:"),
                    div().with(h2(medic.get().getName()))
            ).render();
            return Answer.ok(html);
        } else {
            String json = dataToJson(medic.get());
            return Answer.ok(json);
        }
    }

}