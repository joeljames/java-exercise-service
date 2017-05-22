package com.java.exercise.service;

import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.java.exercise.service.dao.ActivityDAO;
import com.java.exercise.service.dao.ActivityDAOImpl;
import com.java.exercise.service.model.Activity;
import com.java.exercise.service.rest.ActivityResource;
import com.java.exercise.service.service.MorphiaService;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class GuiceServletModule extends JerseyServletModule {

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
    @Named("MorphiaService")
    public MorphiaService getMorphiaService() {
        return new MorphiaService();
    }

    @Provides
    @Singleton
    @Inject
    @Named("ActivityDAO")
    public ActivityDAO getActivityDAO(@Named("MorphiaService") MorphiaService morphiaService) {
        return new ActivityDAOImpl(Activity.class, morphiaService.getDatastore());
    }
}
