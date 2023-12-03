package interface_adapter.weather;


import entity.Weather;

public class WeatherState {
    private String username = "";

    private Weather weather;
    private String usernameError = null;

    private String location = "";
    private String locationError = null;


    public WeatherState(WeatherState copy){
        username = copy.username;
        usernameError = copy.usernameError;
        location = copy.location;
        locationError = copy.locationError;
    }
    public WeatherState() {}

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLocation(String location){this.location = location;}
    public String getLocation(){return this.location;}

    public void setWeather(Weather weather){this.weather = weather;}
    public Weather getWeather(){return this.weather;}

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getLocationError() {return locationError;}

    public void setLocationError(String error) {this.locationError = error;}



}










