package interface_adapter.search_usergroup;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_group.CreateGroupState;
import interface_adapter.create_group.CreateGroupViewModel;
import use_case.search_usergroup.SearchGroupOutputBoundary;
import use_case.search_usergroup.SearchGroupOutputData;

public class SearchUserGroupPresenter implements SearchGroupOutputBoundary {
    private final SearchUserGroupViewModel searchViewModel;
    private ViewManagerModel viewManagerModel;
    public SearchUserGroupPresenter(ViewManagerModel viewManagerModel, SearchUserGroupViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    public void prepareSuccessView(SearchGroupOutputData response) {
        SearchGroupState searchGroupState = searchViewModel.getState();
        searchGroupState.setUsername(response.getUsername());
        this.searchViewModel.setState(searchGroupState);
        this.searchViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(searchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
    public void prepareFailView(String error) {

    }
}
