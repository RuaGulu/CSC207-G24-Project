package interface_adapter.signup;

public class SignupState {

    private String username = "";
    private String usernameError = null;
    private String location = "";

    private String group = "";
    private String locationError = null;

    private boolean isNewGroup = true;

    public SignupState(SignupState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        location = copy.location;
        locationError = copy.locationError;
    }

    public SignupState() {
    }

    public String getUsername() {return username;}

    public String getUsernameError() {return usernameError;}

    public String getLocation() {return location;}

    public String getLocationError() {return locationError;}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLocationError(String locationError) {
        this.locationError = locationError;
    }

    public void setGroup(String group){
        this.group = group;
    }

    public String getGroup(){
        return group;
    }

    public void joinGroup(){this.isNewGroup = false; }

    public boolean getGroupCondition(){return isNewGroup;}

    @Override
    public String toString() {
        return "SignupState{" +
                "username='" + username + '\'' +
                ", location='" + location + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}