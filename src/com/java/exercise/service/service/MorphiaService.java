package com.java.exercise.service.service;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

/**
 * Holds Morphia and Datastore instances.
 */
public class MorphiaService {

    private Morphia morphia;
    private Datastore datastore;

    public MorphiaService() {
        MongoClient mongoClient = new MongoClient("127.0.0.1:27017");

        this.morphia = new Morphia();
        String databaseName = "exercise_service";
        this.datastore = morphia.createDatastore(mongoClient, databaseName);
    }

    public Morphia getMorphia() {
        return morphia;
    }

    public void setMorphia(Morphia morphia) {
        this.morphia = morphia;
    }

    public Datastore getDatastore() {
        return datastore;
    }

    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }

}
