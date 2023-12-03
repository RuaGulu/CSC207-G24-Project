package interface_adapter.logged_in;

public class LoggedInState {
    private String username = "";
    private String groupName = "";
    private String property = "";

    private String location = "";

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {}

    public String getUsername() {
        return username;
    }

    public String getGroupName(){return groupName;}
    public void setGroupName(String groupName){this.groupName = groupName;}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProperty(String name){
        this.property = name;
    }

    public String getProperty(){
        return property;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

}
