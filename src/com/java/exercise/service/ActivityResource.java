package com.java.exercise.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.java.exercise.service.dao.ActivityDAO;
import com.java.exercise.service.model.Activity;

@Path("activities")  // http://localhost:8071/java-exercise-service/api/activities
public class ActivityResource {
        
    @Inject
    @Named("ActivityDAO")
    private ActivityDAO activityDAO;;

    @GET // http://localhost:8071/java-exercise-service/api/activities?descriptions=Swimming&durationFrom=10&durationTo=60
    @Produces({ MediaType.APPLICATION_JSON })
    public Response serachForActivities(@QueryParam(value = "descriptions") List<String> descriptions,
            @QueryParam(value = "durationFrom") int durationFrom, @QueryParam(value = "durationTo") int durationTo) {
        System.out.println("Descriptions: " + descriptions);
        System.out.println("Duration From: " + durationFrom);
        System.out.println("Duration To: " + durationTo);

        List<Activity> activities = activityDAO.findAllActivities();
        if (activities == null || activities.size() <= 0) {
            Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {
        }).build();
    }

    @GET // http://localhost:8071/java-exercise-service/api/activities/123
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("{activityId}")
    public Response getActivityUser(@PathParam("activityId") String activityId) {
        Activity activity = activityDAO.findByActivityId(activityId);
        if (activity == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok().entity(activity).build();
    }

    @POST // http://localhost:8071/java-exercise-service/api/activities/
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response createActivity(String body) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> jsonMap = new HashMap<String, Object>();
            jsonMap = mapper.readValue(body, new TypeReference<Map<String, String>>() {
            });
            System.out.println("The jsonMap is: " + jsonMap);
            String id = (String) jsonMap.get("id");
            Integer duration = Integer.parseInt((String) jsonMap.get("duration"));
            String description = (String) jsonMap.get("description");

            activityDAO.create(id, description, duration);
        } catch (IOException ie) {
            ie.printStackTrace();
            return Response.status(Status.BAD_REQUEST).build();
        }
        return Response.status(Status.CREATED).build();
    }

    @DELETE // http://localhost:8071/java-exercise-service/api/activities/
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("{activityId}")
    public Response deleteActivity(@PathParam("activityId") String activityId) {
        activityDAO.delete(activityId);
        return Response.ok().build();
    }
}
