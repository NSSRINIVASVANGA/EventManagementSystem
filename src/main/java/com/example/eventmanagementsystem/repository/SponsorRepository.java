package com.example.eventmanagementsystem.repository;

import com.example.eventmanagementsystem.model.*;
import java.util.*;

public interface SponsorRepository {

    ArrayList<Sponsor> getSponsors();

    Sponsor addSponsor(Sponsor sponsor);

    Sponsor getSponsorById(int sponsorId);

    Sponsor updateSponsor(int SponsorId, Sponsor sponsor);

    void deleteSponsor(int sponsorId);

    List<Event> getSponsorEvents(int sponsorId);
}