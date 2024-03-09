package com.example.eventmanagementsystem.controller;

import com.example.eventmanagementsystem.model.*;
import com.example.eventmanagementsystem.repository.*;
import com.example.eventmanagementsystem.service.*;

import java.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class SponsorController {
    @Autowired
    public SponsorJpaService sponsorJpaService;

    @GetMapping("/events/sponsors")
    public ArrayList<Sponsor> getSponsors() {
        return sponsorJpaService.getSponsors();
    }

    @PostMapping("/events/sponsors")
    public Sponsor addSponsor(@RequestBody Sponsor sponsor) {
        return sponsorJpaService.addSponsor(sponsor);
    }

    @GetMapping("/events/sponsors/{sponsorId}")
    public Sponsor getSponsorById(@PathVariable("sponsorId") int sponsorId) {
        return sponsorJpaService.getSponsorById(sponsorId);
    }

    @PutMapping("/events/sponsors/{sponsorId}")
    public Sponsor updateSponsor(@PathVariable("sponsorId") int sponsorId, @RequestBody Sponsor sponsor) {
        return sponsorJpaService.updateSponsor(sponsorId, sponsor);
    }

    @DeleteMapping("/events/sponsors/{sponsorId}")
    public void deleteSponsor(@PathVariable("sponsorId") int sponsorId) {
        sponsorJpaService.deleteSponsor(sponsorId);
    }

    @GetMapping("/sponsors/{sponsorId}/events")
    public List<Event> getSponsorEvents(@PathVariable("sponsorId") int sponsorId) {
        return sponsorJpaService.getSponsorEvents(sponsorId);
    }
}