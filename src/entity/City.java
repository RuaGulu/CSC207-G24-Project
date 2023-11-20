package entity;

public class City {
    private String name;
    private double longitude;
    private double latitude;

    // Constructor to initialize the city object.
    public City(String name, double longitude, double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    // Getter for the name field.
    public String getCityName() {
        return name;
    }

    // Getter for the longitude field.
    public double getLongitude() {
        return longitude;
    }

    // Getter for the latitude field.
    public double getLatitude() {
        return latitude;
    }

    // If you decide that the city's name or its coordinates can change,
    // you can also add setters. Here's an example setter for the name field:
    public void setName(String name) {
        this.name = name;
    }

    // Example setter for the longitude field.
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // Example setter for the latitude field.
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    // You may also want to override the toString method to provide a string representation of the city.
    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    // Depending on your application's needs, you might also want to override equals and hashCode.
    // Implementing these depends on whether two city objects with the same name, longitude, and latitude should be considered equal.
}
