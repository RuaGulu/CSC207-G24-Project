package interface_adapter.create_group;

import entity.User;
import use_case.create_group.CreatGroupInputBoundary;
import use_case.create_group.CreateGroupInputData;
import use_case.search_usergroup.SearchGroupInputBoundary;
import use_case.search_usergroup.SearchGroupInputData;

import java.util.List;

public class CreateGroupController {
    final CreatGroupInputBoundary inputdata;


    public CreateGroupController(CreatGroupInputBoundary inputdata) {
        this.inputdata = inputdata;
    }

    public void execute(String groupname, String users) {
        CreateGroupInputData createGroupInputData = new CreateGroupInputData(users, groupname);
        inputdata.execute(createGroupInputData);
    }
}
