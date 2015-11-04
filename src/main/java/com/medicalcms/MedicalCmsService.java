package com.medicalcms;

import com.beust.jcommander.JCommander;
import com.medicalcms.anamneses.AnamneseSql2oModel;
import com.medicalcms.anamneses.handlers.CreateAnamneseHandler;
import com.medicalcms.anamneses.handlers.DeleteAnamneseHandler;
import com.medicalcms.anamneses.handlers.EditAnamneseHandler;
import com.medicalcms.anamneses.handlers.GetSingleAnamneseHandler;
import com.medicalcms.medics.handlers.GetSingleMedicHandler;
import com.medicalcms.medics.*;


import com.medicalcms.medics.handlers.CreateMedicHandler;
import com.medicalcms.medics.handlers.DeleteMedicHandler;
import com.medicalcms.medics.handlers.EditMedicHandler;
import com.medicalcms.medics.handlers.IndexMedicsHandler;
import com.medicalcms.patients.PatientSql2oModel;

import com.medicalcms.patients.handlers.*;
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

        get("/patients/:id", new GetSinglePatientHandler(patientSql2oModel));
        get("/patients", new IndexPatientsHandler(patientSql2oModel));
        post("/patients", new CreatePatientHandler(patientSql2oModel));
        put("/patients/:id", new EditPatientHandler(patientSql2oModel));
        delete("/patients/:id", new DeletePatientHandler(patientSql2oModel));

        get("/anamneses/:id", new GetSingleAnamneseHandler(anamneseSql2oModel));
        post("/anamneses", new CreateAnamneseHandler(anamneseSql2oModel));
        put("/anamneses/:id", new EditAnamneseHandler(anamneseSql2oModel, medicSql2oModel, patientSql2oModel));
        delete("/anamneses/:id", new DeleteAnamneseHandler(anamneseSql2oModel));

        get("/alive", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "ok";
            }
        });
    }
}
