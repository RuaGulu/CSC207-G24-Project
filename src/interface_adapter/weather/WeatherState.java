package interface_adapter.weather;


import entity.Weather;

public class WeatherState {
    private String username = "";

    private Weather weather;
    private String usernameError = null;


    public WeatherState(WeatherState copy){
        username = copy.username;
        usernameError = copy.usernameError;
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

    public void setWeather(Weather weather){this.weather = weather;}

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

}


