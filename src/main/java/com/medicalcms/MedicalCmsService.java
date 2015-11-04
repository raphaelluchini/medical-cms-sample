package com.medicalcms;

import com.beust.jcommander.JCommander;
import com.medicalcms.anamneses.AnamneseSql2oModel;
import com.medicalcms.handlers.GetSingleMedicHandler;
import com.medicalcms.medics.*;


import com.medicalcms.patients.PatientSql2oModel;

import org.sql2o.Sql2o;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.logging.Logger;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;
import static spark.SparkBase.port;

public class MedicalCmsService
{

    private static final Logger logger = Logger.getLogger(MedicalCmsService.class.getCanonicalName());

    public static void main( String[] args) {
        CommandLineOptions options = new CommandLineOptions();
        new JCommander(options, args);

        logger.finest("Options.debug = " + options.debug);
        logger.finest("Options.database = " + options.database);
        logger.finest("Options.dbHost = " + options.dbHost);
        logger.finest("Options.dbUsername = " + options.dbUsername);
        logger.finest("Options.dbPort = " + options.dbPort);

        port(options.servicePort);

        Sql2o sql2o = new Sql2o("jdbc:mysql://" + options.dbHost + ":" + options.dbPort + "/" + options.database,
                options.dbUsername, options.dbPassword
        );

        AnamneseSql2oModel anamneseSql2oModel = new AnamneseSql2oModel(sql2o);
        MedicSql2oModel medicSql2oModel = new MedicSql2oModel(sql2o);
        PatientSql2oModel patientSql2oModel = new PatientSql2oModel(sql2o);

        get("/medics/:id", new GetSingleMedicHandler(medicSql2oModel));
        get("/medics", new IndexMedicsHandler(medicSql2oModel));
        post("/medics", new CreateMedicHandler(medicSql2oModel));
        put("/medics/:id", new EditMedicHandler(medicSql2oModel));
        delete("/medics/:id", new DeleteMedicHandler(medicSql2oModel));

        get("/alive", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "ok";
            }
        });
    }
}
