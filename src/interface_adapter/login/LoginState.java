package interface_adapter.login;

public class LoginState {
    private String username = "";

    private String location = "";
    private String usernameError = null;

    private String group = "";

    private String keyName = "";


    public LoginState(LoginState copy){
        username = copy.username;
        usernameError = copy.usernameError;
    }
    public LoginState() {}

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLocation(String location){this.location = location;}

    public String getLocation(){return location;}

    public void setGroup(String group){this.group = group;}
    public String getGroup(){return group;}

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }
    public void setKeyName (String text){
        this.keyName = text;
    }
    public String getKeyName(){
        return keyName;
    }

}

