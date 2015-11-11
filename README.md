# MedicCmsSample
Medical CMS sample using Java

##Getting Started OSX/Linux:
```bash
git clone https://github.com/raphaelluchini/medical-cms-sample.git
cd medical-cms-sample
mvn compile && mvn exec:java
```
##Using IDEs:

###Eclipse:
```
File > Import > Maven > Existing Maven Projects > (Browse your project folder) > Finish
Run > Run
```

###NetBeans:
```
File > Open Project > (Select your project folder)
Run
```

Open in your browser: http://localhost:4567/

##Database
Database connect information are located in com/medicalcms/CommandLineOptions
Check if they are the same of your computer if you need to override default configurations, you can use those following params:

--debug
--database
--db-host
--db-username
--db-password
--db-port

For example:
```bash
mvn compile && mvn exec:java -Dexec.args="--db-username someuser --db-password password"
```

##Creating mySQL database
```bash
mysql < medicalcms.sql
```
Or use any database software that supports mySQL