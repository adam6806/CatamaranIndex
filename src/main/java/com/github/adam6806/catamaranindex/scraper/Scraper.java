package com.github.adam6806.catamaranindex.scraper;

import com.github.adam6806.catamaranindex.database.model.BoatEntity;
import com.github.adam6806.catamaranindex.database.service.BoatService;
import com.github.adam6806.catamaranindex.scraper.boatsite.BoatSite;
import com.github.adam6806.catamaranindex.scraper.boatsite.BoatSiteFactory;
import com.github.adam6806.catamaranindex.scraper.email.EmailHtmlGenerator;
import com.github.adam6806.catamaranindex.scraper.email.EmailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Scraper {

    @Inject
    private BoatService boatService;

    @Inject
    private BoatSiteFactory boatSiteFactory;

    @Inject
    private EmailSender emailSender;

    @Scheduled(cron = "0 0 7,12,16 ? * *")
    public void scrape() throws IOException {

        List<BoatSite> boatSites = boatSiteFactory.getBoatSites();
        List<BoatEntity> newBoats = new ArrayList<>();
        for (BoatSite boatSite : boatSites) {
            saveBoatEntities(boatSite, newBoats);
        }
        if (!newBoats.isEmpty()) {
            emailSender.sendEmail(EmailHtmlGenerator.generateHTML(newBoats));
        }
    }

    private void saveBoatEntities(BoatSite boatSite, List<BoatEntity> newBoats) {
        List<BoatEntity> boatEntities = boatSite.getBoatEntities();
        for (BoatEntity boatEntity : boatEntities) {
            BoatEntity boatEntityQuery = boatService.findByUrl(boatEntity.getUrl());
            if (boatEntityQuery == null) {
                boatService.save(boatEntity);
                newBoats.add(boatEntity);
            }
        }
    }
}