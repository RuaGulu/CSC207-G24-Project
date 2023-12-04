package interface_adapter;

import interface_adapter.air_quality.AirQualityController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.air_quality.AirQualityInputBoundary;
import use_case.air_quality.AirQualityInputData;

import static org.junit.jupiter.api.Assertions.*;

class AirQualityInputBoundaryStub implements AirQualityInputBoundary {
    public boolean executeCalled = false;

    @Override
    public void execute(AirQualityInputData inputData) {
        this.executeCalled = true;
    }
}

public class AirQualityControllerTest {

    private AirQualityController controller;
    private AirQualityInputBoundaryStub inputBoundaryStub;

    @BeforeEach
    public void setUp() {
        inputBoundaryStub = new AirQualityInputBoundaryStub();
        controller = new AirQualityController(inputBoundaryStub);
    }

    @Test
    public void testExecute() {
        String testLocation = "Test Location";

        controller.execute(testLocation);

        assertTrue(inputBoundaryStub.executeCalled);
    }
}
