package com.java.exercise.service.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

import com.java.exercise.service.model.Activity;


public interface ActivityDAO extends DAO<Activity, ObjectId> {

    List<Activity> findAllActivities();

    List<Activity> getByDescription(String description);

    Activity findByActivityId(String activityId);

    void create(String id, String description, int duration);

    void delete(String activityId);

    List<Activity> findByDescriptions(List<String> descriptions, int durationFrom, int durationTo);

}
