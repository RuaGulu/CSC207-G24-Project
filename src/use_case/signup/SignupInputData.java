package use_case.signup;

public class SignupInputData {

    final private String username;
    final private String location;

    public SignupInputData(String username, String location) {
        this.username = username;
        this.location = location;
    }

    String getUsername() {return username;}

    String getLocation() {return location;}
}
