package use_case.login;

public class LoginInputData {
    final private String username;

    public LoginInputData(String username){
        this.username = username;
    }
    String getUsername(){
        return username;
    }

}
