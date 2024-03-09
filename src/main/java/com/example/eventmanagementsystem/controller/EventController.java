package com.example.eventmanagementsystem.controller;

import com.example.eventmanagementsystem.model.*;
import com.example.eventmanagementsystem.repository.*;
import com.example.eventmanagementsystem.service.*;

import java.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class EventController {
    @Autowired
    public EventJpaService eventJpaService;

    @GetMapping("/events")
    public ArrayList<Event> getEvents() {
        return eventJpaService.getEvents();
    }

    @PostMapping("/events")
    public Event addEvent(@RequestBody Event event) {
        return eventJpaService.addEvent(event);
    }

    @GetMapping("/events/{eventId}")
    public Event getEventById(@PathVariable("eventId") int eventId) {
        return eventJpaService.getEventById(eventId);
    }

    @PutMapping("/events/{eventId}")
    public Event updateEvent(@PathVariable("eventId") int eventId, @RequestBody Event event) {
        return eventJpaService.updateEvent(eventId, event);
    }

    @DeleteMapping("/events/{eventId}")
    public void deleteEvent(@PathVariable("eventId") int eventId) {
        eventJpaService.deleteEvent(eventId);
    }

    @GetMapping("/events/{eventId}/sponsors")
    public List<Sponsor> getEventSponsors(@PathVariable("eventId") int eventId) {
        return eventJpaService.getEventSponsors(eventId);
    }

}