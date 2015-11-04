package com.medicalcms.medics.handlers;

import com.medicalcms.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.handlers.EmptyPayload;
import com.medicalcms.medics.MedicModel;

import java.util.Map;
import java.util.stream.Collectors;

import static j2html.TagCreator.*;

public class IndexMedicsHandler extends AbstractRequestHandler<EmptyPayload> {
    public MedicModel model;
    public IndexMedicsHandler(MedicModel model) {
        super(EmptyPayload.class);
        this.model = model;
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map urlParams, boolean shouldReturnHtml) {
        if (shouldReturnHtml) {
            String html = body().with(
                    h1("Medics:"),
                    div().with(
                            model.getAll().stream().map((p) ->
                                    div().with(
                                            h2(p.getName())
                                            ))
                                    .collect(Collectors.toList()))
            ).render();
            return Answer.ok(html);
        } else {
            String json = dataToJson(model.getAll());
            return Answer.ok(json);
        }
    }

}
