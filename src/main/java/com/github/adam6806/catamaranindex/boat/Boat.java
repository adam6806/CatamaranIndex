package com.github.adam6806.catamaranindex.boat;

public class Boat {

    private Long price;
    private String makeModel;
    private String location;
    private String image;
    private String link;
    private int length;
    private int year;
    private int id;
    private int dougRating;
    private int adamRating;
    private String timestamp;

    public Boat(Long price, String makeModel, String location, String image, String link, int length, int year, int id, int dougRating, int adamRating, String timestamp) {
        this.price = price;
        this.makeModel = makeModel;
        this.location = location;
        this.image = image;
        this.link = link;
        this.length = length;
        this.year = year;
        this.id = id;
        this.dougRating = dougRating;
        this.adamRating = adamRating;
        this.timestamp = timestamp;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDougRating() {
        return dougRating;
    }

    public void setDougRating(int dougRating) {
        this.dougRating = dougRating;
    }

    public int getAdamRating() {
        return adamRating;
    }

    public void setAdamRating(int adamRating) {
        this.adamRating = adamRating;
    }

    public int getRating(String username) {
        if ("adam".equals(username)) {
            return getAdamRating();
        } else {
            return getDougRating();
        }
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
