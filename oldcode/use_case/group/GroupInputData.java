package use_case.group;

public class GroupInputData {
    final private String user;

    final private String location;

    public GroupInputData(String user, String location){
        this.user = user;
        this.location = location;
    }
    String getUser() { return user;}

    String getLocation(){return location;}

}