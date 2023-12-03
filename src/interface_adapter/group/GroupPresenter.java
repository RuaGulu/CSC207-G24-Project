package interface_adapter.group;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.group.GroupOutputBoundary;
import use_case.group.GroupOutputData;

public class GroupPresenter implements GroupOutputBoundary {
    private final GroupViewModel groupViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    public GroupPresenter(ViewManagerModel viewManagerModel, GroupViewModel groupViewModel, LoggedInViewModel loggedInViewModel) {
        this.groupViewModel = groupViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    public void prepareSuccessView(GroupOutputData groupOutputData) {
        //
        System.out.println("到达group presenter");

        GroupState state = groupViewModel.getState();
        state.setData(groupOutputData.getData());
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setProperty("group");
        this.groupViewModel.setState(state);
        this.groupViewModel.firePropertyChanged();
        this.loggedInViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(groupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String error) {
        GroupState groupState = groupViewModel.getState();
        groupState.setGroupnameError(error);
        groupViewModel.firePropertyChanged();
    }


}