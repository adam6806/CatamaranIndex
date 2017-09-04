package com.github.adam6806.catamaranindex.controller;

import com.github.adam6806.catamaranindex.scraper.Scraper;
import com.github.adam6806.catamaranindex.scraper.boatsite.BoatSite;
import com.github.adam6806.catamaranindex.scraper.boatsite.BoatSiteFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@Scope(value = "session")
public class ScraperTestController {

    @Autowired
    private BoatSiteFactory boatSiteFactory;

    @Autowired
    private Scraper scraper;

    @RequestMapping(value = "test-scraper", method = RequestMethod.GET)
    public String testScraper(ModelMap model) {
        return "testScraper";
    }

    @RequestMapping(value="testScraperResults", method = RequestMethod.POST)
    public String scraper(ModelMap modelMap, @RequestParam(value = "scraper") String scraper) throws IOException {
        BoatSite boatSite = boatSiteFactory.getBoatSite(scraper);
        modelMap.addAttribute("scrapedBoats", boatSite.getBoatEntities());
        modelMap.addAttribute("scraper", scraper);
        return "testScraperResults";
    }

    @RequestMapping(value = "runScraper", method = RequestMethod.GET)
    public String chooseSite() {
        try {
            scraper.scrape();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }

}
