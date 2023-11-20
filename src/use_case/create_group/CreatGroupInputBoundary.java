package use_case.create_group;

import use_case.login.LoginInputData;

public interface CreatGroupInputBoundary {
    void execute(CreateGroupInputData CreateGroupInputData);
}
