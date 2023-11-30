package interface_adapter.search_usergroup;

import use_case.search_usergroup.SearchGroupInputBoundary;
import use_case.search_usergroup.SearchGroupInputData;

public class SearchUserGroupController {
    final SearchGroupInputBoundary searchGroupUseCaseInteractor;

    public SearchUserGroupController(SearchGroupInputBoundary searchGroupUseCaseInteractor) {
        this.searchGroupUseCaseInteractor = searchGroupUseCaseInteractor;
    }
    public void execute(String username) {
        SearchGroupInputData searchGroupInputData = new SearchGroupInputData(username);
        searchGroupUseCaseInteractor.execute(searchGroupInputData);
    }

}
