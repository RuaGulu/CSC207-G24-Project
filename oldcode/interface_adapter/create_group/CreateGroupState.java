package interface_adapter.create_group;

import entity.User;
import interface_adapter.signup.SignupState;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupState {
    private String groupname = "";
    private String groupnameError = null;
    private String user = "";
    private String usererror = null;

    public CreateGroupState(CreateGroupState copy) {
        groupname = copy.groupname;
        groupnameError = copy.groupnameError;
        user = copy.user;
        usererror = copy.usererror;
    }

    public CreateGroupState() {
    }

    public String getGroupname() {return groupname;}

    public String getGroupnameErrorr() {return groupnameError;}

    public String getUser() {return user;}

    public String getUserError() {return usererror;}

    public void setGroupname(String Groupname) {
        this.groupname = groupname;
    }
    public void setUser(String user){this.user = user;}
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
