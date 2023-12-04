package use_case.air_quality;

import api.WeatherDB;

public class AirQualityInteractor implements AirQualityInputBoundary{

    final WeatherDB airDataAccessObject;

    final AirQualityOutputBoundary airQualityPresenter;

    public AirQualityInteractor(WeatherDB airDataAccessObject,
                                AirQualityOutputBoundary airQualityPresenter) {
        this.airDataAccessObject = airDataAccessObject;
        this.airQualityPresenter = airQualityPresenter;
    }

    @Override
    public void execute(AirQualityInputData airQualityInputData) {
        AirQualityOutputData airQualityOutputData = new AirQualityOutputData(airDataAccessObject.getAirQuality(airQualityInputData.getLocation()));
        airQualityPresenter.prepareSuccessView(airQualityOutputData);
    }
}
