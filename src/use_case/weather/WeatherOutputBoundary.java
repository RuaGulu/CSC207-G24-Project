package use_case.Weather;

public interface WeatherOutputBoundary {

    void prepareSuccessView(WeatherOutputData location);

    void prepareFailView(String error);
}