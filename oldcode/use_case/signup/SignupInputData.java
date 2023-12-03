package use_case.signup;

public class SignupInputData {

    final private String username;
    final private String location;

    final private String group;

    final private boolean isNewGroup;

    public SignupInputData(String username, String location, String group, boolean isNewGroup) {
        this.username = username;
        this.location = location;
        this.group = group;
        this.isNewGroup = isNewGroup;
    }

    String getUsername() {return username;}

    String getLocation() {return location;}

    String getGroup(){
        return group;
    }

    boolean isNewGroup(){return isNewGroup;}
}
