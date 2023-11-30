package use_case.create_group;

import entity.Group;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupInputData {
    final private String user;
    final private String group_name;

    public CreateGroupInputData(String user, String group_name){
        this.user = user;
        this.group_name = group_name;
    }
    String getGroupname(){
        return group_name;
    }
    String getUser() { return user;}

}
