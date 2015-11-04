package com.medicalcms.anamneses.handlers;

import com.medicalcms.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.anamneses.Anamnese;
import com.medicalcms.anamneses.AnamneseModel;
import com.medicalcms.handlers.EmptyPayload;

import java.util.Map;
import java.util.Optional;

import static j2html.TagCreator.*;


public class GetSingleAnamneseHandler extends AbstractRequestHandler<EmptyPayload> {
    public AnamneseModel model;

    public GetSingleAnamneseHandler(AnamneseModel model) {
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

        Optional<Anamnese> anamnese = model.get(id);

        if (anamnese == null) {
            return new Answer(404);
        }

        if (shouldReturnHtml) {
            String html = body().with(
                    h1("Anamneses"),
                    div().with(h2(anamnese.get().getDate().toString()))
            ).render();
            return Answer.ok(html);
        } else {
            String json = dataToJson(anamnese.get());
            return Answer.ok(json);
        }
    }

}
