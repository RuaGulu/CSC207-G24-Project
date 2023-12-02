package use_case.air_quality;

import entity.AirQuality;

public class AirQualityOutputData {

    private final AirQuality airQuality;

    public AirQualityOutputData(AirQuality airQuality) {this.airQuality = airQuality;}

    public AirQuality getAirQuality() {return airQuality;}
}
