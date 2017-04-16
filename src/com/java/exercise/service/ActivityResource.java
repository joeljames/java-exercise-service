package com.java.exercise.service;

import java.util.List;

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

import com.java.exercise.service.dao.ActivityDAO;
import com.java.exercise.service.dao.ActivityDAOImpl;
import com.java.exercise.service.model.Activity;
import com.java.exercise.service.service.MorphiaService;

@Path("activities")  // http://localhost:8071/java-exercise-service/webapi/activities
public class ActivityResource {

    private MorphiaService morphiaService = new MorphiaService();
    private ActivityDAO activityDAO = new ActivityDAOImpl(Activity.class, morphiaService.getDatastore());
    
    @GET // http://localhost:8071/java-exercise-service/webapi/activities?descriptions=Swimming&durationFrom=10&durationTo=60
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

    @GET // http://localhost:8071/java-exercise-service/webapi/activities/123
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("{activityId}")
    public Response getActivityUser(@PathParam("activityId") String activityId) {
        Activity activity = activityDAO.findByActivityId(activityId);
        if (activity == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok().entity(activity).build();
    }

    @POST // http://localhost:8071/java-exercise-service/webapi/activities/
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response createActivity(Activity activity) {
        System.out.println(activity.getDescription());
        activityDAO.create(activity.getId(), activity.getDescription(), activity.getDuration());
        return Response.status(Status.CREATED).build();
    }
    
    @DELETE // http://localhost:8071/java-exercise-service/webapi/activities/
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{activityId}")
    public Response deleteActivity(@PathParam("activityId") String activityId){
        activityDAO.delete(activityId);
        return Response.ok().build();
    }
}
