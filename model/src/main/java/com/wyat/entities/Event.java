package com.wyat.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.Serializable;

public class Event implements Serializable{

    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("event_pic_url")
    @Expose
    private String eventPicUrl;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("event_type")
    @Expose
    private String eventType;
    @SerializedName("invite_only")
    @Expose
    private String inviteOnly;
    @SerializedName("free")
    @Expose
    private String free;
    @SerializedName("age_restriction")
    @Expose
    private String ageRestriction;
    @SerializedName("ticket_price")
    @Expose
    private String ticketPrice;
    @SerializedName("user")
    @Expose
    private int user;



    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Event withVenue(Venue venue) {
        this.venue = venue;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Event withName(String name) {
        this.name = name;
        return this;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Event withTime(String time) {
        this.time = time;
        return this;
    }

    public String getEventPicUrl() {
        return eventPicUrl;
    }

    public void setEventPicUrl(String eventPicUrl) {
        this.eventPicUrl = eventPicUrl;
    }

    public Event withEventPicUrl(String eventPicUrl) {
        this.eventPicUrl = eventPicUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Event withEventType(String eventType) {
        this.eventType = eventType;
        return this;
    }

    public String getInviteOnly() {
        return inviteOnly;
    }

    public void setInviteOnly(String inviteOnly) {
        this.inviteOnly = inviteOnly;
    }



    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }


    public String getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }


    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }


    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }


}