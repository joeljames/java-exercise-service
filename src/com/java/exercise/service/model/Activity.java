package com.java.exercise.service.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("activity")
@XmlRootElement
public class Activity {

    @Id
    private String id;
    private String description;
    private int duration;

    /**
     * keep an empty constructor so that morphia can recreate this entity when you want to fetch it from the database
     */
    public Activity() {
    }

    /**
     * full constructor (without objectId, we let morphia generate this one for us)
     * 
     * @param description
     * @param duration
     */
    public Activity(String id, String description, int duration) {
        super();
        this.id = id;
        this.description = description;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "desc")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}