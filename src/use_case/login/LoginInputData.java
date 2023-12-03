package use_case.login;

public class LoginInputData {
    final private String username;

    final private String condition;

    final private String location;

    public LoginInputData(String username, String location, String condition){
        this.username = username;
        this.condition = condition;
        this.location = location;
    }
    String getUsername(){
        return username;
    }

    String getCondition(){return condition;}

    String getLocation(){return location;}

}