package com.github.adam6806.catamaranindex.boat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class BoatRequest {

    private static final String URL = "http://www.yachtworld.com/core/listing/cache/searchResults.jsp?toPrice=175000&fromPrice=25000&enid=101&Ntk=boatsEN&type=%28Sail%29+Catamaran&searchtype=advancedsearch&hmid=0&sm=3&enid=0&cit=true&luom=126&currencyid=100&boatsAddedSelected=-1&ftid=0&slim=quick&No=0&rid=100&rid=104&rid=105&rid=107&rid=112&rid=115&rid=125&fracts=1&ps=2000&Ns=PBoat_sortByPriceDesc|1";
    private static final String MAIN_URL = "http://www.yachtworld.com";
    int lowPrice;
    private ArrayList<Boat> boats;
    private int highPrice;

    public ArrayList<Boat> getBoats() throws IOException {
        boats = new ArrayList<>();
        Document doc = Jsoup.connect(URL).get();
        System.out.println(doc.title());
        Elements listRows = doc.select(".listing-container");
        for (Element element : listRows) {
            if (element.select(".price").text().startsWith("US")) {
                Element link = element.select("a[href]").first();
                String boatLink = MAIN_URL + link.attr("href");
                String price = element.select(".price").text();
                price = price.substring(2);
                String makeModel = element.select(".make-model").text();
                String location = element.select(".location").text();
                String image = element.select("img").attr("src");
                Boat boat = new Boat(price, makeModel, location, image, boatLink);
                boats.add(boat);
            }
        }
        return boats;
    }

    public void setBoats(ArrayList<Boat> boats) {
        this.boats = boats;
    }

    public int getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(int highPrice) {
        this.highPrice = highPrice;
    }

    public int getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(int lowPrice) {
        this.lowPrice = lowPrice;
    }

}
