package com.java.exercise.service.repository;

import java.util.List;

import com.java.exercise.service.model.Activity;

public interface ActivityRepository {
    List<Activity> findAllActivities();

    Activity findByActivityId(String activityId);

    void create(Activity activity);

    void delete(String activityId);

    List<Activity> findByDescriptions(List<String> descriptions, int durationFrom, int durationTo);

}
