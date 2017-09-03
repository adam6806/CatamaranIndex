package com.github.adam6806.catamaranindex.controller;

import com.github.adam6806.catamaranindex.database.model.BoatEntity;
import com.github.adam6806.catamaranindex.scraper.boatsite.YachtWorldBoatSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope(value = "session")
public class ScraperTestController {

    private final String YAHCT_WORLD = "yachtWorld";
    @Autowired
    private YachtWorldBoatSite yachtWorldBoatSite;
    @RequestMapping(value = "test-scraper", method = RequestMethod.GET)
    public String testScraper(ModelMap model) {
        return "testScraper";
    }

    @RequestMapping(value="testScraperResults", method = RequestMethod.POST)
    public String scraper(ModelMap modelMap, @RequestParam(value = "scraper") String scraper) throws IOException {
        List<BoatEntity> entityList = new ArrayList<>();
        switch (scraper){
            case YAHCT_WORLD:
                    entityList = yachtWorldBoatSite.getBoatEntities();
                    break;
            default:
                break;
        }
        modelMap.addAttribute("scrapedBoats", entityList);
        modelMap.addAttribute("scraper", scraper);
        return "testScraperResults";
    }
}