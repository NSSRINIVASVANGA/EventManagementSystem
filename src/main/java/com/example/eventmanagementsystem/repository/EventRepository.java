package com.example.eventmanagementsystem.repository;

import com.example.eventmanagementsystem.model.*;

import java.util.*;

public interface EventRepository {

    ArrayList<Event> getEvents();

    Event addEvent(Event event);

    Event getEventById(int eventId);

    Event updateEvent(int eventId, Event event);

    void deleteEvent(int eventId);

    List<Sponsor> getEventSponsors(int eventId);
}