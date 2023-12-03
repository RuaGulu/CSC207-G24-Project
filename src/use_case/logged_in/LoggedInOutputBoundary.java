package use_case.logged_in;

import use_case.Weather.WeatherOutputData;

public interface LoggedInOutputBoundary {

    void prepareSuccessView(LoggedInOutputData location);

    void prepareFailView(String error);
}
