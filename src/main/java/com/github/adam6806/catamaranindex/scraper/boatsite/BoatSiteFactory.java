package com.github.adam6806.catamaranindex.scraper.boatsite;

import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoatSiteFactory {

    public static final String YACHT_WORLD = "yachtWorld";
    @Inject
    private YachtWorldBoatSite yachtWorldBoatSite;

    public BoatSite getBoatSite(String boatSite) {
        switch (boatSite) {
            case YACHT_WORLD:
                return yachtWorldBoatSite;
            default:
                throw new UnsupportedOperationException("This boat site does not exist. Please use constants available in BoatSiteFactoryClass");
        }
    }

    public List<BoatSite> getBoatSites() {
        List<BoatSite> boatSites = new ArrayList<>();
        boatSites.add(yachtWorldBoatSite);
        return boatSites;
    }
}
