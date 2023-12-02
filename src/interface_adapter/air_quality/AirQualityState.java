package interface_adapter.air_quality;

import entity.AirQuality;

public class AirQualityState {

    private AirQuality airQuality;
    private String location = "";
    private String locationError = null;

    public AirQualityState(AirQualityState copy) {
        location = copy.location;
        locationError = copy.locationError;
    }

    public AirQualityState() {}

    public AirQuality getAirQuality() {return airQuality;}
    public void setAirQuality(AirQuality airQuality) {this.airQuality = airQuality;}

    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}

    public String getLocationError() {return locationError;}

    public void setLocationError(String error) {this.locationError = error;}


}
