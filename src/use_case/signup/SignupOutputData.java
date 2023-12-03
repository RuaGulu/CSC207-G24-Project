package use_case.signup;

public class SignupOutputData {

    private final String username;

    private String creationTime;

    private String groupName;

    private boolean useCaseFailed;

    public SignupOutputData(String username, String creationTime, String groupName, boolean useCaseFailed) {
        this.username = username;
        this.creationTime = creationTime;
        this.useCaseFailed = useCaseFailed;
        this.groupName = groupName;
    }

    public String getUsername() {return username;};

    public String getCreationTime() {return creationTime;}

    public String getGroupName(){return groupName;}

    public void setCreationTime(String creationTime) {this.creationTime = creationTime;}

}
