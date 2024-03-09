package com.example.eventmanagementsystem.service;

import com.example.eventmanagementsystem.model.*;
import com.example.eventmanagementsystem.repository.*;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class EventJpaService implements EventRepository {
    @Autowired
    public EventJpaRepository eventJpaRepository;

    @Autowired
    public SponsorJpaRepository sponsorJpaRepository;

    @Override
    public ArrayList<Event> getEvents() {
        List<Event> eventsList = eventJpaRepository.findAll();
        ArrayList<Event> events = new ArrayList<>(eventsList);
        return events;
    }

    @Override
    public Event addEvent(Event event) {
        try {
            List<Integer> sponsorIds = new ArrayList<>();

            for (Sponsor sponsor : event.getSponsors()) {
                sponsorIds.add(sponsor.getSponsorId());
            }

            List<Sponsor> sponsorsList = sponsorJpaRepository.findAllById(sponsorIds);
            event.setSponsors(sponsorsList);

            for (Sponsor sponsor : sponsorsList) {
                sponsor.getEvents().add(event);
            }

            Event savedEvent = eventJpaRepository.save(event);
            sponsorJpaRepository.saveAll(sponsorsList);
            return savedEvent;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Event getEventById(int eventId) {
        try {
            Event event = eventJpaRepository.findById(eventId).get();
            return event;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Event updateEvent(int eventId, Event event) {
        try {
            Event newEvent = eventJpaRepository.findById(eventId).get();

            if (event.getEventName() != null) {
                newEvent.setEventName(event.getEventName());
            }

            if (event.getDate() != null) {
                newEvent.setDate(event.getDate());
            }

            if (event.getSponsors() != null) {
                List<Sponsor> oldSponsors = newEvent.getSponsors();

                for (Sponsor sponsor : oldSponsors) {
                    sponsor.getEvents().remove(newEvent);
                }

                sponsorJpaRepository.saveAll(oldSponsors);

                List<Integer> sponsorIds = new ArrayList<>();

                for (Sponsor sponsor : event.getSponsors()) {
                    sponsorIds.add(sponsor.getSponsorId());
                }

                List<Sponsor> newSponsorsList = sponsorJpaRepository.findAllById(sponsorIds);

                for (Sponsor sponsor : newSponsorsList) {
                    sponsor.getEvents().add(newEvent);
                }

                sponsorJpaRepository.saveAll(newSponsorsList);
                newEvent.setSponsors(newSponsorsList);
            }
            return eventJpaRepository.save(newEvent);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public void deleteEvent(int eventId) {
        try {
            Event event = eventJpaRepository.findById(eventId).get();
            List<Sponsor> sponsorsList = event.getSponsors();

            for (Sponsor sponsor : sponsorsList) {
                sponsor.getEvents().remove(event);
            }

            sponsorJpaRepository.saveAll(sponsorsList);
            eventJpaRepository.deleteById(eventId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Sponsor> getEventSponsors(int eventId) {
        try {
            Event event = eventJpaRepository.findById(eventId).get();
            return event.getSponsors();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}