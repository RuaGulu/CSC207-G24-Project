package interface_adapter.air_quality;

import use_case.air_quality.AirQualityInputBoundary;
import use_case.air_quality.AirQualityInputData;

public class AirQualityController {
    final AirQualityInputBoundary airQualityInputInteractor;

    public AirQualityController(AirQualityInputBoundary airQualityInputInteractor) {
        this.airQualityInputInteractor = airQualityInputInteractor;
    }

    public void execute(String location) {
        AirQualityInputData inputData = new AirQualityInputData(location);

        airQualityInputInteractor.execute(inputData);
    }
}
