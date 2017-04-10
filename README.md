## Java Exercise Service
This project is a basic jersey project skeleton. I am using ivy package manager to pull down  project dependencies. I will be using Eclipse editor, and will be running this project using Jetty.

# Add Ivy to Eclipse
1. Under Help -> Install New Software
2. Add a new Update site
3. Set Name to "Ivy"
4. Set Location to http://www.apache.org/dist/ant/ivyde/updatesite [See: http://ant.apache.org/ivy/ivyde/download.cgi]
5. Click OK
6. Select "Apache Ivy library" and "Apache IvyDE Eclipse plugins" -> Next -> Next
7. Accept Agreements
8. Click Finish
9. When prompted, restart eclipse

# Add Jetty to Eclipse
1. Under Help -> Eclipse Marketplace
2. Search for Jetty
3. Select Run-Jetty-Run
4. Select install

# Clone the project
```
git clone https://github.com/joeljames/java-exercise-service.git
```

# Resolve the ivy dependencies specied in ivy.xml file
Ivy uses the [maven 2]((http://mvnrepository.com/search?q=jersey-bundle)) repository to resolve the dependencies you declare in the ivy.xml file.
1. File -> New -> Java Project
2. Set Project Name to "java-exercise-service"
3. Package Explorer -> Right click on the project name "java-exercise-service"
4. Build Path -> Configure Build Path ->
5. Libraries(tab) -> Add Library -> Select (ivyDE Managed dependencies ) -> Next -> Finish

# Run the project using Jetty
1. Run -> Run Configurations
2. Right click -> Jetty Webapp -> New
3. Set Select a Jetty Version to "Jetty 7.6"
4. Set Project to "java-exercise-service"
5. Set Port to "8071"
6. Set context to "/java-exercise-service"
7. Set WebApp dir "web"
8. Apply -> Run

The app should be up and running now. To exercise the web service, open a browser to the URL or use curl to exercise the URL:
```
http://localhost:8071/java-exercise-service/webapi/activities?descriptions=Swimming&durationFrom=10&durationTo=60
```

