package com.medicalcms;

import com.beust.jcommander.Parameter;

class CommandLineOptions {

    @Parameter(names = "--debug")
    boolean debug = false;

    @Parameter(names = {"--database"})
    String database = "medicalcms";

    @Parameter(names = {"--db-host"})
    String dbHost = "localhost";

    @Parameter(names = {"--db-username"})
    String dbUsername = "root";

    @Parameter(names = {"--db-password"})
    String dbPassword = "";

    @Parameter(names = {"--db-port"})
    Integer dbPort = 3306;
}
