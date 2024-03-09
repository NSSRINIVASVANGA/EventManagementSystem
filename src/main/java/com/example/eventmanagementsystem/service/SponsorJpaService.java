package com.example.eventmanagementsystem.service;

import com.example.eventmanagementsystem.model.*;
import com.example.eventmanagementsystem.repository.*;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class SponsorJpaService implements SponsorRepository {
    @Autowired
    public SponsorJpaRepository sponsorJpaRepository;

    @Autowired
    public EventJpaRepository eventJpaRepository;

    @Override
    public ArrayList<Sponsor> getSponsors() {
        List<Sponsor> sponsorsList = sponsorJpaRepository.findAll();
        ArrayList<Sponsor> sponsors = new ArrayList<>(sponsorsList);
        return sponsors;
    }

    @Override
    public Sponsor addSponsor(Sponsor sponsor) {
        try {
            List<Integer> eventIds = new ArrayList<>();

            for (Event event : sponsor.getEvents()) {
                eventIds.add(event.getEventId());
            }

            List<Event> eventsList = eventJpaRepository.findAllById(eventIds);

            if (eventIds.size() != eventsList.size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            sponsor.setEvents(eventsList);

            return sponsorJpaRepository.save(sponsor);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Sponsor getSponsorById(int sponsorId) {
        try {
            Sponsor sponsor = sponsorJpaRepository.findById(sponsorId).get();
            return sponsor;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Sponsor updateSponsor(int sponsorId, Sponsor sponsor) {
        try {
            Sponsor newSponsor = sponsorJpaRepository.findById(sponsorId).get();
            if (sponsor.getSponsorName() != null) {
                newSponsor.setSponsorName(sponsor.getSponsorName());
            }

            if (sponsor.getIndustry() != null) {
                newSponsor.setIndustry(sponsor.getIndustry());
            }

            if (sponsor.getEvents() != null) {
                List<Integer> eventIds = new ArrayList<>();
                for (Event event : sponsor.getEvents()) {
                    eventIds.add(event.getEventId());
                }

                List<Event> eventsList = eventJpaRepository.findAllById(eventIds);

                if (eventIds.size() != eventsList.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
                newSponsor.setEvents(eventsList);
            }
            return sponsorJpaRepository.save(newSponsor);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteSponsor(int sponsorId) {
        try {
            sponsorJpaRepository.deleteById(sponsorId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Event> getSponsorEvents(int sponsorId) {
        try {
            Sponsor sponsor = sponsorJpaRepository.findById(sponsorId).get();
            return sponsor.getEvents();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}