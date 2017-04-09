package com.java.exercise.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
 
@Path("myresource")
public class MyResource {
    public static final String CLICHED_MESSAGE = "Hello World!";
 
    @GET
    @Produces("text/plain")
    public String getHello() {
        return CLICHED_MESSAGE;
    }
}
