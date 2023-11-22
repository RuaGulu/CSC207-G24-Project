package use_case.login;

public class LoginOutputData {
    private final String username;
    private final String location;
    private boolean useCaseFailed;

    public LoginOutputData(String username, String location, boolean useCaseFailed) {
        this.username = username;
        this.location = location;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getLocation() { return location; }
}
