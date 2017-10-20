package travelers.feed.info_feed;

import java.util.Currency;

public class Destinations {
    private String name;
    private String address;
    private String rating;
    private String expense;
    private String currency;
    private double longitude;
    private double latitude;
    private String imageUrl;

    public Destinations() {
    }

    public Destinations(String name, String address, String rating, String expense, String currency, double longitude, double latitude, String imageUrl) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.expense = expense;
        this.currency = currency;
        this.longitude = longitude;
        this.latitude = latitude;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.expense = currency;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
