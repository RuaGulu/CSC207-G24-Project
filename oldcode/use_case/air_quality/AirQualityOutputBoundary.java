package use_case.air_quality;

public interface AirQualityOutputBoundary {

    void prepareSuccessView(AirQualityOutputData location);

    void prepareFailView(String error);
}