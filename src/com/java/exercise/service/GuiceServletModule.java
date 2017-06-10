package com.java.exercise.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Singleton;

import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.java.exercise.service.config.ApplicationProperties;
import com.java.exercise.service.dao.ActivityDAO;
import com.java.exercise.service.dao.ActivityDAOImpl;
import com.java.exercise.service.model.Activity;
import com.java.exercise.service.rest.ActivityResource;
import com.java.exercise.service.service.MorphiaService;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class GuiceServletModule extends JerseyServletModule {

    private static final String CONFIG_PROPERTIES = "config.properties";
    private static final Logger LOGGER = Logger.getLogger(GuiceServletModule.class.getName());

    @Override
    protected void configureServlets() {
        super.configureServlets();

        /* Bind the REST resources */
        bind(ActivityResource.class).in(Singleton.class);

        serve("/api/*").with(GuiceContainer.class);

    }

    @Provides
    @Singleton
    @Inject
    @Named("Properties")
    public ApplicationProperties getApplicationProperties() {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES);

        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            LOGGER.log(Level.SEVERE, "Failed to load properties.");
            throw new RuntimeException("Failed to load Application Properties. Quitting.");
        }
        return new ApplicationProperties(properties);
    }

    @Provides
    @Singleton
    @Inject
    @Named("MorphiaService")
    public MorphiaService getMorphiaService(@Named("Properties") ApplicationProperties properties) {
        return new MorphiaService(properties.getDbURL());
    }

    @Provides
    @Singleton
    @Inject
    @Named("ActivityDAO")
    public ActivityDAO getActivityDAO(@Named("MorphiaService") MorphiaService morphiaService) {
        return new ActivityDAOImpl(Activity.class, morphiaService.getDatastore());
    }
}
