# Java Exercise Service
This project is a basic jersey project skeleton. I wll be using ivy package manager to pull down project dependencies. I will be using Eclipse editor, and will be running this project using Jetty.

## Prerequisite

### Install ant (1.9.9)
```
mkdir -p /usr/loacl/src # I personally store all the lib under "/usr/local/src"
wget http://supergsego.com/apache//ant/binaries/apache-ant-1.9.9-bin.zip -P /usr/local/src
unzip /usr/local/src/apache-ant-1.9.9-bin.zip -d /usr/local/src
rm /usr/local/src/apache-ant-1.9.9-bin.zip
echo 'export PATH="/usr/local/src/apache-ant-1.9.9/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

### Add Ivy jar to ant lib
We need to add ivy jar to the ant lib directory so that ant can resolve ivy dependencies usign the <ivy:resolve/> tag.
```
wget http://search.maven.org/remotecontent?filepath=org/apache/ivy/ivy/2.2.0/ivy-2.2.0.jar -P /usr/local/src/apache-ant-1.9.9/lib
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
```
git clone https://github.com/joeljames/java-exercise-service.git
```

### Add the project to Eclipse
1. File -> New -> Java Project
2. Set Project Name to "java-exercise-service"

### Build the project using command line
Run the below command to retrives ivy dependencies, compile source and output the classes to build/main
```
ant compile
```

Or you can resolve ivy dependencies using Eclipse. Ivy uses the [maven 2]((http://mvnrepository.com/)) repository to resolve the dependencies you declare in the ivy.xml file.

1. Package Explorer -> Right click on the project name "java-exercise-service"
2. Build Path -> Configure Build Path ->
3. Libraries(tab) -> Add Library -> Select (ivyDE Managed dependencies ) -> Next -> Finish

### Run unit tests
```
ant test
```

### Start the project using Jetty
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
