package com.java.exercise.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.java.exercise.service.model.Activity;
import com.java.exercise.service.repository.ActivityRepository;
import com.java.exercise.service.repository.ActivityRepositoryStub;

@Path("activities")  //http://localhost:8071/java-exercise-service/webapi/activities
public class ActivityResource {
    private ActivityRepository activityRepository = new ActivityRepositoryStub();
    
    @GET //http://localhost:8071/java-exercise-service/webapi/activities?descriptions=Swimming&durationFrom=10&durationTo=60
    @Produces({ MediaType.APPLICATION_JSON})
    public Response serachForActivities(
            @QueryParam(value="descriptions") List<String> descriptions,
            @QueryParam(value="durationFrom") int durationFrom,
            @QueryParam(value="durationTo") int durationTo){
        System.out.println("Descriptions: " + descriptions);
        System.out.println("Duration From: " + durationFrom);
        System.out.println("Duration To: " + durationTo);

        List<Activity> activities = activityRepository.findByDescriptions(
                descriptions, durationFrom, durationTo);
        if (activities == null || activities.size() <= 0){
            Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok().entity(new GenericEntity<List<Activity>>(activities){}).build();
    }
}
