package use_case.outpudata;

import org.junit.Before;
import org.junit.Test;
import use_case.signup.SignupOutputData;

public class SignupOutputDataTest {
    private String username = "test";

    private String creationTime = "2023-12-4";

    private String groupName = "testgroup";

    @Test
    public void testSignupOutputData(){
        SignupOutputData signupOutputData = new SignupOutputData(username, creationTime,groupName,true);
        assert (signupOutputData.getUsername().equals("test"));
        assert (signupOutputData.getCreationTime().equals(creationTime));
        assert (signupOutputData.getGroupName().equals(groupName));
        signupOutputData.setCreationTime("2023-12-5");
        assert (signupOutputData.getCreationTime().equals("2023-12-5"));
    }


}
