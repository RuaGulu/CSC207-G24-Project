package interface_adapter;

import interface_adapter.login.LoginController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

import static org.junit.jupiter.api.Assertions.*;

class LoginInputBoundaryStub implements LoginInputBoundary {
    public boolean executeCalled = false;

    @Override
    public void execute(LoginInputData inputData) {
        this.executeCalled = true;
    }
}

public class LoginControllerTest {
    private LoginController controller;
    private LoginInputBoundaryStub inputBoundaryStub;

    @BeforeEach
    public void setUp() {
        inputBoundaryStub = new LoginInputBoundaryStub();
        controller = new LoginController(inputBoundaryStub);
    }

    @Test
    public void testExecute() {
        controller.execute("username", "location", "groupName", "condition", true, "text");
        assertTrue(inputBoundaryStub.executeCalled);
    }
}
