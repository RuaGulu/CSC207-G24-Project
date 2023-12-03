package use_case.login;

public class LoginOutputData {
    private final String username;

    private final String location;
    private boolean useCaseFailed;

    private final String groupName;

    public LoginOutputData(String username, String location, String groupName, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.location = location;
        this.groupName = groupName;
    }

    public String getUsername() {
        return username;
    }

    public String getLocation(){return location;}

    public String getGroupName(){return groupName;}
}
