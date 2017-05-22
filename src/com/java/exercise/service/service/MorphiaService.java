package com.java.exercise.service.service;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.java.exercise.service.config.ApplicationProperties;
import com.mongodb.MongoClient;

/**
 * Holds Morphia and Datastore instances.
 */
public class MorphiaService {

    @Inject
    @Named("Properties")
    private ApplicationProperties properties;
    
    private static final String EXERCISE_SERVICE = "exercise_service";

    private final Morphia morphia;
    private final Datastore datastore;


    public MorphiaService(String dbURL) {
        MongoClient mongoClient = new MongoClient(dbURL);

        this.morphia = new Morphia();
        String databaseName = EXERCISE_SERVICE;
        this.datastore = morphia.createDatastore(mongoClient, databaseName);
    }

    public Morphia getMorphia() {
        return morphia;
    }

    public Datastore getDatastore() {
        return datastore;
    }

}
