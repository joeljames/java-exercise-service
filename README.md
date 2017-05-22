# Java Exercise Service
This is a starter webservice project in Java using morphia(mongodb) and jersey. This project was created as a part of me learning and understanding how to develop an webservice application in Java. I have used ivy package manager to pull down project dependencies. I will be using Eclipse editor, and will be providing instructions to start the application using Jetty and Tomcat.

Please feel free to clone or fork and use as needed.

## Prerequisite

### Install ant (1.9.9)
```bash
mkdir -p /usr/loacl/src # I personally store all the lib under "/usr/local/src"
wget http://supergsego.com/apache//ant/binaries/apache-ant-1.9.9-bin.zip -P /usr/local/src
unzip /usr/local/src/apache-ant-1.9.9-bin.zip -d /usr/local/src
rm /usr/local/src/apache-ant-1.9.9-bin.zip
echo 'export PATH="/usr/local/src/apache-ant-1.9.9/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

### Install Mongo
If you are using mac and have brew package manager installed. You can install Mongo by running the command below:

```bash
brew update
brew install mongodb
```

### Add Ivy jar to ant lib
We need to add ivy jar to the ant lib directory so that ant can resolve ivy dependencies usign the <ivy:resolve/> tag.

```bash
wget http://search.maven.org/remotecontent?filepath=org/apache/ivy/ivy/2.2.0/ivy-2.2.0.jar -P /usr/local/src/apache-ant-1.9.9/lib
```

### Install Tomcat (This is optional, only required if you want to the application on a Tomcat server)

```bash
wget http://apache.mesi.com.ar/tomcat/tomcat-7/v7.0.77/bin/apache-tomcat-7.0.77.zip -P /usr/local/src
unzip /usr/local/src/apache-tomcat-7.0.77.zip -d /usr/local/src
rm /usr/local/src/apache-tomcat-7.0.77.zip
echo 'export CATALINA_HOME=/usr/local/src/apache-tomcat-7.0.77' >> ~/.zshrc
source ~/.zshrc
chmod +x $CATALINA_HOME/bin//bin/*.sh
```

### Add Ivy to Eclipse
1. Under Help -> Install New Software
2. Add a new Update site
3. Set Name to "Ivy"
4. Set Location to http://www.apache.org/dist/ant/ivyde/updatesite [See: http://ant.apache.org/ivy/ivyde/download.cgi]
5. Click OK
6. Select "Apache Ivy library" and "Apache IvyDE Eclipse plugins" -> Next -> Next
7. Accept Agreements
8. Click Finish
9. When prompted, restart eclipse

### Add Jetty to Eclipse
1. Under Help -> Eclipse Marketplace
2. Search for Jetty
3. Select Run-Jetty-Run
4. Select install

## Getting Strted

### Clone the project to your workspace directory
```bash
git clone https://github.com/joeljames/java-exercise-service.git
```

### Add the project to Eclipse
1. File -> New -> Java Project
2. Set Project Name to "java-exercise-service"

### Build the project using command line
Run the below command to retrives ivy dependencies, compile source and output the classes to build/main

```bash
ant compile
```

Or you can resolve ivy dependencies using Eclipse. Ivy uses the [maven 2]((http://mvnrepository.com/)) repository to resolve the dependencies you declare in the ivy.xml file.

1. Package Explorer -> Right click on the project name "java-exercise-service"
2. Build Path -> Configure Build Path ->
3. Libraries(tab) -> Add Library -> Select (ivyDE Managed dependencies ) -> Next -> Finish
4. Right click on the project name "java-exercise-service" -> Ivy -> Resolve

### Run unit tests
```bash
ant test
```

### Start the project
You can start the project either by using Jetty or by using Tomcat.

#### Using Jetty
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
http://localhost:8071/java-exercise-service/api/activities?descriptions=Swimming&durationFrom=10&durationTo=60
```

#### Using Tomcat
```bash
ant war
cp package/java-exercise-service.war $CATALINA_HOME/webapps/
sh $CATALINA_HOME/bin/startup.sh
```
Now the Tomcat server should be up and running on localhost at port 8080. Tail the catalina log to se if the application started without any errors. You can tail the logs  by running the command below:

```bash
tail -f $CATALINA_HOME/logs/catalina.out
```

If needed to stop the Tomcat server you can do that by runnign the command below:

```bash
sh $CATALINA_HOME/bin/shutdown.sh
```

The app should be up and running now. To exercise the web service, open a browser to the URL or use curl to exercise the URL:

```
http://localhost:8080/java-exercise-service/api/activities
```

