package use_case.Weather;

public class WeatherInputData {
    final private String location;

    public WeatherInputData(String location){
        this.location = location;
    }

    String getLocation(){
        return location;
    }
}
