package com.medicalcms.requestsHandlers;

import com.medicalcms.requestsHandlers.AbstractRequestHandler;
import com.medicalcms.Answer;
import com.medicalcms.EmptyPayload;
import spark.ModelAndView;
import java.util.Map;


public class TemplateHandler extends AbstractRequestHandler<EmptyPayload> {
    public String template;
    public TemplateHandler(String template) {
        super(EmptyPayload.class);
        this.template = template;
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String,String> urlParams, boolean shouldReturnHtml) {
        if (shouldReturnHtml) {
            String html = toHandlebars(new ModelAndView(null, this.template));
            return Answer.ok(html);
        } else {
            return new Answer(404);
        }
    }

}
