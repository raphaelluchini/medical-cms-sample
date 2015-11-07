package com.medicalcms.requestsHandlers;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.medicalcms.Answer;
import com.medicalcms.RequestHandler;
import com.medicalcms.Validable;
import com.medicalcms.EmptyPayload;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public abstract class MultipartRequestHandler<V extends Validable> implements RequestHandler<V>, Route {

    private Class<V> valueClass;

    private static final int HTTP_BAD_REQUEST = 400;

    public MultipartRequestHandler(Class<V> valueClass) {
        this.valueClass = valueClass;
    }

    private static boolean shouldReturnHtml(Request request) {
        String accept = request.headers("Accept");
        return accept != null;
    }

    public static String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException("IOException from a StringWriter?");
        }
    }

    public final Answer process(V value, Map<String, String> urlParams, boolean shouldReturnHtml) {
        if (value != null && !value.isValid()) {
            return new Answer(HTTP_BAD_REQUEST);
        } else {
            return processImpl(value, urlParams, shouldReturnHtml);
        }
    }

    protected abstract Answer processImpl(V value, Map<String, String> urlParams, boolean shouldReturnHtml);

    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            String location = "/tmp";
            ObjectMapper objectMapper = new ObjectMapper();
            V value = null;
            if (valueClass != EmptyPayload.class) {
                MultipartConfigElement multipartConfigElement = new MultipartConfigElement(location);
                request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
                Part file = request.raw().getPart("file"); //file is name of the upload form
                String num = Integer.toString((int) Math.round(Math.random() * 10000));
                String filename = num + "-" + file.getSubmittedFileName();
                Path out = Paths.get(location + "/" + num + "-" + file.getSubmittedFileName());
                try (final InputStream in = file.getInputStream()) {
                    Files.copy(in, out);
                    file.delete();
                }
                Map<String, String[]> map = request.raw().getParameterMap();
                Map<String, String> hm = new HashMap<String, String>();

                for (Map.Entry<String, String[]> item : map.entrySet()) {
                    hm.put(item.getKey(), item.getValue()[0]);
                }
                hm.put("src", "/" + filename);
                value = objectMapper.readValue(dataToJson(hm), valueClass);
            }
            Map<String, String> urlParams = request.params();
            Answer answer = process(value, urlParams, false);
            response.status(answer.getCode());
            return answer.getBody();
        } catch (JsonMappingException e) {
            System.out.println(e);
            response.status(400);
            response.body(e.getMessage());
            return e.getMessage();
        }
    }

}
