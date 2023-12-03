package interface_adapter.logged_in;

import use_case.Weather.WeatherOutputData;
import use_case.logged_in.LoggedInOutputBoundary;
import use_case.logged_in.LoggedInOutputData;

public class LoggedInPresenter implements LoggedInOutputBoundary {


    @Override
    public void prepareSuccessView(LoggedInOutputData data) {

    }

    @Override
    public void prepareFailView(String error){

    }
}
