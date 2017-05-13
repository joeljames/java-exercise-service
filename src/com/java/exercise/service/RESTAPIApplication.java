package com.java.exercise.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.java.exercise.service.utils.SystemExitReturnValue;

@ApplicationPath("/")
public class RESTAPIApplication extends Application {

    public static final String PROPERTIES_FILE = "config.properties";
    public static Properties properties = new Properties();

    public RESTAPIApplication() {
        System.out.println("Application starting up...");
    }

    private Properties readProperties() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");

        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to load properties.");
            System.exit(SystemExitReturnValue.FAILURE.getReturnCode());
        }
        return properties;
    }
    
    

    @Override
    public Set<Class<?>> getClasses() {
        // Read the properties file
        readProperties();

        // Set up your Jersey resources
        Set<Class<?>> rootResources = new HashSet<Class<?>>();
        
        // Add your resources here
        rootResources.add(ActivityResource.class);
        return rootResources;
    }
}
