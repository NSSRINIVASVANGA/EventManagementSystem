package com.example.eventmanagementsystem.model;

import com.example.eventmanagementsystem.model.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int eventId;
    @Column(name = "name")
    private String eventName;
    @Column(name = "date")
    private String date;
    @ManyToMany(mappedBy = "events")
    @JsonIgnoreProperties("events")
    private List<Sponsor> sponsors = new ArrayList<>();

    public Event() {
    }

    public Event(int eventId, String eventName, String date, List<Sponsor> sponsors) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.date = date;
        this.sponsors = sponsors;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDate() {
        return date;
    }

    public List<Sponsor> getSponsors() {
        return sponsors;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSponsors(List<Sponsor> sponsors) {
        this.sponsors = sponsors;
    }
}