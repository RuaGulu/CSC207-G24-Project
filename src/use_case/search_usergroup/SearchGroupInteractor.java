package use_case.search_usergroup;

import entity.Group;

import entity.User;

import use_case.login.LoginUserDataAccessInterface;

import java.time.LocalDateTime;
import java.util.List;

public class SearchGroupInteractor implements SearchGroupInputBoundary{

    final SearchGroupOutputBoundary searchGroupPresenter;

    final SearchGroupUserDataAccess getUser;

    public SearchGroupInteractor(SearchGroupOutputBoundary searchGroupPresenter, SearchGroupUserDataAccess getUser) {
        this.searchGroupPresenter = searchGroupPresenter;
        this.getUser = getUser;
    }

    public void execute(SearchGroupInputData groupInputData) {
        String username = groupInputData.getUser();
        if (!getUser.existsByName(username)){
            searchGroupPresenter.prepareFailView(username + ": User does not exist");
        }
        User user = getUser.get(username);
        List<Group> usergroup = user.getGroup();
        SearchGroupOutputData searchGroupOutputData = new SearchGroupOutputData(username, usergroup,false);
        searchGroupPresenter.prepareSuccessView(searchGroupOutputData);
        }
}
