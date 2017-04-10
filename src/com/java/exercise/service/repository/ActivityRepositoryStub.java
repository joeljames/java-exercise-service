package com.java.exercise.service.repository;

import java.util.ArrayList;
import java.util.List;

import com.java.exercise.service.model.Activity;

public class ActivityRepositoryStub implements ActivityRepository {

    @Override
    public List<Activity> findAllActivities() {
        List<Activity> activities = new ArrayList<>();

        Activity activity1 = new Activity();
        activity1.setId("123");
        activity1.setDescription("Swimming");
        activity1.setDuration(20);
        activities.add(activity1);

        Activity activity2 = new Activity();
        activity2.setId("456");
        activity2.setDescription("Running");
        activity2.setDuration(55);
        activities.add(activity2);
        return activities;
    }

    @Override
    public Activity findByActivityId(String activityId) {
        Activity activity = new Activity();
        activity.setId("123");
        activity.setDescription("Swimming");
        activity.setDuration(20);
        return activity;
    }

    @Override
    public void create(Activity activity) {
        // Insert activity into a database

    }

    @Override
    public void delete(String activityId) {
        // Delete activity from the database

    }

    @Override
    public List<Activity> findByDescriptions(List<String> descriptions, int durationFrom, int durationTo) {        
        List<Activity> activities = new ArrayList<>();
        
        Activity activity1 = new Activity();
        activity1.setId("123");
        activity1.setDescription("Swimming");
        activity1.setDuration(20);
        activities.add(activity1);

        Activity activity2 = new Activity();
        activity2.setId("456");
        activity2.setDescription("Running");
        activity2.setDuration(55);
        activities.add(activity2);        
        return activities;
    }

}
