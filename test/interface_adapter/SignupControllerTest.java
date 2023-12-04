package interface_adapter;

import interface_adapter.signup.SignupController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

import static org.junit.jupiter.api.Assertions.*;

class SignupInputBoundaryStub implements SignupInputBoundary {
    public boolean executeCalled = false;

    @Override
    public void execute(SignupInputData inputData) {
        this.executeCalled = true;
    }
}

public class SignupControllerTest {
    private SignupController controller;
    private SignupInputBoundaryStub inputBoundaryStub;

    @BeforeEach
    public void setUp() {
        inputBoundaryStub = new SignupInputBoundaryStub();
        controller = new SignupController(inputBoundaryStub);
    }

    @Test
    public void testExecute() {
        controller.execute("username", "location", "group", true);
        assertTrue(inputBoundaryStub.executeCalled);
    }
}
