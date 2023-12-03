package use_case.signup;

public class SignupInputData {

    final private String username;
    final private String location;

    final private String group;

    public SignupInputData(String username, String location, String group) {
        this.username = username;
        this.location = location;
        this.group = group;
    }

    String getUsername() {return username;}

    String getLocation() {return location;}

    String getGroup(){
        return group;
    }
}