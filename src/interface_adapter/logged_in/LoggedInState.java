package interface_adapter.logged_in;

public class LoggedInState {


    private String username = "";
    private String location = "";


    public LoggedInState(LoggedInState copy) {
        username = copy.username;
        location = copy.location;

    }

    public LoggedInState() {}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getLocation() {return location;}
    public void setLocation(String location) {
        this.location = location;
    }



}
