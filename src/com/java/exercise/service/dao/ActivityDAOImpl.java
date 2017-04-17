package com.java.exercise.service.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.DAO;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.java.exercise.service.model.Activity;

public class ActivityDAOImpl extends DAO<Activity, ObjectId> implements ActivityDAO  {

    @SuppressWarnings("deprecation")
    public ActivityDAOImpl(Class<Activity> entityClass, Datastore ds) {
        super(entityClass, ds);
    }
    
    @Override
    public List<Activity> findAllActivities() {
        Query<Activity> query = createQuery();
        return query.asList();
    }

    @Override
    public List<Activity> getByDescription(String description) {
        Query<Activity> query = createQuery().field("description").equal(description);
        return query.asList();
    }
    
    @Override
    public Activity findByActivityId(String activityId) {
        return createQuery().field("_id").equal(activityId).get();
    }

    @Override
    public void create(String id, String description, int duration) {
        Activity activity = new Activity(id, description, duration);
        save(activity);        
    }

    @Override
    public void delete(String activityId) {
        Activity activity = findByActivityId(activityId);
        delete(activity);
    }

    @Override
    public List<Activity> findByDescriptions(List<String> descriptions, int durationFrom, int durationTo) {
        return null;
    }
    
}
