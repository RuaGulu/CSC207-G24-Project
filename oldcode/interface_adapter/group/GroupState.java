package interface_adapter.group;

import entity.Weather;

import java.util.HashMap;

public class GroupState {
    private String groupname = "";
    private String groupnameError = null;
    private String user = "";
    private String usererror = null;

    private HashMap<String, HashMap<String,Weather>> data = null;

    public GroupState(GroupState copy) {
        groupname = copy.groupname;
        groupnameError = copy.groupnameError;
        user = copy.user;
        usererror = copy.usererror;
    }

    public GroupState() {
    }

    public String getUser() {return user;}

    public void setUser(String userName){this.user = userName;}

    public void setData(HashMap<String, HashMap<String,Weather>> data){this.data = data;}

    public HashMap<String, HashMap<String,Weather>> getData(){
        return data;
    }

    public void setGroupnameError(String groupnameError) {
        this.groupnameError = groupnameError;
    }

    @Override
    public String toString() {
        return "CreateGroupState{" +
                "groupname='" + groupname + '\'' +
                ", groupcreater='" + user
                + '\'' + '}';
    }
}
