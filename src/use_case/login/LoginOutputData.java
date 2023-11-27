package use_case.login;

public class LoginOutputData {
    private final String username;

    private final String location;
    private boolean useCaseFailed;

    public LoginOutputData(String username, String location, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public String getLocation(){return location;}
}
