package interface_adapter;

import interface_adapter.weather.WeatherController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.Weather.WeatherInputBoundary;
import use_case.Weather.WeatherInputData;

import static org.junit.jupiter.api.Assertions.*;

class WeatherInputBoundaryStub implements WeatherInputBoundary {
    public boolean executeCalled = false;

    @Override
    public void execute(WeatherInputData inputData) {
        this.executeCalled = true;
    }
}

public class WeatherControllerTest {
    private WeatherController controller;
    private WeatherInputBoundaryStub inputBoundaryStub;

    @BeforeEach
    public void setUp() {
        inputBoundaryStub = new WeatherInputBoundaryStub();
        controller = new WeatherController(inputBoundaryStub);
    }

    @Test
    public void testExecute() {
        controller.execute("location");
        assertTrue(inputBoundaryStub.executeCalled);
    }
}
