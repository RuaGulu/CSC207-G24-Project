package interface_adapter.weather;

import entity.Weather;

public class WeatherState {
    private Weather weather;
    private String location = "";
    private String locationError = null;


    public WeatherState(WeatherState copy){
        location = copy.location;
        locationError = copy.locationError;
    }
    public WeatherState() {}

    public Weather getWeather() {return weather;}

    public void setWeather(Weather weather){this.weather = weather;}

    public void setLocation(String location) {this.location = location;}
    public String getLocation() {return location;}

    public String getLocationError() {return locationError;}
    public void setLocationError(String error) {this.locationError = error;}
}


