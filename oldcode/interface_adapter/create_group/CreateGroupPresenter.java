package interface_adapter.create_group;

import interface_adapter.ViewManagerModel;

import interface_adapter.logged_in.LoggedInViewModel;

import use_case.create_group.CreateGroupOutputBoundary;
import use_case.create_group.CreateGroupOutputData;

public class CreateGroupPresenter implements CreateGroupOutputBoundary {
    private final CreateGroupViewModel creategroupViewModel;
    private ViewManagerModel viewManagerModel;

    public CreateGroupPresenter(ViewManagerModel viewManagerModel, CreateGroupViewModel creategroupViewModel) {
        this.creategroupViewModel = creategroupViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(CreateGroupOutputData groupOutputData) {

        CreateGroupState creategroupState = creategroupViewModel.getState();
        creategroupState.setGroupname(groupOutputData.getGroup_name());
        creategroupViewModel.setState(creategroupState);
        creategroupViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(creategroupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        }

    public void prepareFailView(String error) {
        CreateGroupState creategroupState = creategroupViewModel.getState();
        creategroupState.setGroupnameError(error);
        creategroupViewModel.firePropertyChanged();
    }


}
