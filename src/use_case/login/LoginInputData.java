package use_case.login;

public class LoginInputData {
    final private String username;

    final private String condition;

    final private String location;

    final private String groupName;

    final private boolean groupCondition;

    final private String KeyValue;

    public LoginInputData(String username, String location, String groupName, String condition, boolean groupCondition, String text){
        this.username = username;
        this.condition = condition;
        this.location = location;
        this.groupName = groupName;
        this.groupCondition = groupCondition;
        this.KeyValue = text;
    }
    String getUsername(){
        return username;
    }

    String getCondition(){return condition;}

    String getLocation(){return location;}

    String getGroupName(){return groupName;}

    boolean getGroupCondition(){return groupCondition;}

    String getKeyValue(){return KeyValue;}

}
