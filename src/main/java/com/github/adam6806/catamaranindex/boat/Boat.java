package com.github.adam6806.catamaranindex.boat;

import java.util.Arrays;
import java.util.List;

public class Boat {

    private String price;
    private String makeModel;
    private String location;
    private String image;
    private String link;

    public Boat(String price, String makeModel, String location, String image, String link) {
        this.price = price;
        this.makeModel = makeModel;
        this.location = location;
        this.image = image;
        this.link = link;
    }

    public Boat(String row) {

        List<String> items = Arrays.asList(row.split("\\|"));
        this.price = items.get(0);
        this.makeModel = items.get(1);
        this.location = items.get(2);
        this.image = items.get(3);
        this.link = items.get(4);
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public String getHTML() {
        return "<div>" + getPrice() + " - " + getMakeModel() + " - " + getLocation() + " <br /> <a href=\"" + getLink() + "\"><img src=\"" + getImage() + "\"></a><br /><br />";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Boat boat = (Boat) o;

        if (getPrice() != null ? !getPrice().equals(boat.getPrice()) : boat.getPrice() != null) return false;
        if (getMakeModel() != null ? !getMakeModel().equals(boat.getMakeModel()) : boat.getMakeModel() != null)
            return false;
        if (getLocation() != null ? !getLocation().equals(boat.getLocation()) : boat.getLocation() != null)
            return false;
        return getLink().equals(boat.getLink());
    }

    @Override
    public int hashCode() {
        int result = getPrice() != null ? getPrice().hashCode() : 0;
        result = 31 * result + (getMakeModel() != null ? getMakeModel().hashCode() : 0);
        result = 31 * result + (getLocation() != null ? getLocation().hashCode() : 0);
        result = 31 * result + getLink().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return price + "|" + makeModel + "|" + location + "|" + image + "|" + link;
    }
}
