package interface_adapter.air_quality;

import entity.AirQuality;

public class AirQualityState {

    private String username = "";
    private AirQuality airQuality;
    private String usernameError = null;
    private String location = "";

    public AirQualityState(AirQualityState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
    }

    public AirQualityState() {}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getUsernameErro() {return usernameError;}
    public void setUsernameError(String usernameError) {this.usernameError = usernameError;}

    public AirQuality getAirQuality() {return airQuality;}
    public void setAirQuality(AirQuality airQuality) {this.airQuality = airQuality;}

    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}


}
