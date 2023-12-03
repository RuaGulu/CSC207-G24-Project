package interface_adapter.group;

import use_case.group.GroupInputBoundary;
import use_case.group.GroupInputData;

public class GroupController {
    final GroupInputBoundary groupUseCaseInteractor;

    public GroupController(GroupInputBoundary groupUseCaseInteractor) {
        this.groupUseCaseInteractor = groupUseCaseInteractor ;
    }

    public void execute(String userName, String location) {
        GroupInputData groupInputData = new GroupInputData(userName,location);
        groupUseCaseInteractor.execute(groupInputData);

    }
}