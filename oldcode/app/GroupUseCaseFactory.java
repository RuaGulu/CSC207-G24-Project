package app;

import entity.CommonGroupFactory;

import entity.GroupFactory;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_group.CreateGroupController;
import interface_adapter.create_group.CreateGroupPresenter;
import interface_adapter.create_group.CreateGroupViewModel;

import interface_adapter.search_usergroup.SearchUserGroupController;
import interface_adapter.search_usergroup.SearchUserGroupPresenter;
import interface_adapter.search_usergroup.SearchUserGroupViewModel;

import use_case.create_group.*;
import use_case.search_usergroup.SearchGroupInputBoundary;
import use_case.search_usergroup.SearchGroupInteractor;
import use_case.search_usergroup.SearchGroupOutputBoundary;
import use_case.search_usergroup.SearchGroupUserDataAccess;
import view.GroupView;

import javax.swing.*;
import java.io.IOException;

public class GroupUseCaseFactory {
    private GroupUseCaseFactory() {
    }

    public static GroupView create(
            ViewManagerModel viewManagerModel,
            CreateGroupViewModel groupViewModel,
            SearchUserGroupViewModel searchUserGroupViewModel,
            GroupFindUserDataAccessInterface userDataAccessObject,
            GroupDataAccessInterface groupDataAccessObject,
            SearchGroupUserDataAccess searchGroupUserDataAccess) {
        try {
            CreateGroupController createGroupController = createGroupUseCase(viewManagerModel, groupViewModel, userDataAccessObject, groupDataAccessObject);
            SearchUserGroupController searchUserGroupController = createSearchUseCase(viewManagerModel,searchUserGroupViewModel, searchGroupUserDataAccess);
            return new GroupView(createGroupController, searchUserGroupController, groupViewModel, searchUserGroupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static CreateGroupController createGroupUseCase(ViewManagerModel viewManagerModel, CreateGroupViewModel groupViewModel, GroupFindUserDataAccessInterface userDataAccessObject, GroupDataAccessInterface groupDataAccessInterface) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        CreateGroupOutputBoundary groupOutputBoundary = new CreateGroupPresenter(viewManagerModel, groupViewModel);
        GroupFactory groupFactory = new CommonGroupFactory();
        CreatGroupInputBoundary createGroupInteractor = new CreateGroupInteractor(groupDataAccessInterface, groupOutputBoundary, groupFactory, userDataAccessObject);

        return new CreateGroupController(createGroupInteractor);
    }

    private static SearchUserGroupController createSearchUseCase(
            ViewManagerModel viewManagerModel,
            SearchUserGroupViewModel searchUserGroupViewModel,
            SearchGroupUserDataAccess searchGroupUserDataAccess) throws IOException {

        SearchGroupOutputBoundary searchGroupOutputBoundary = new SearchUserGroupPresenter(viewManagerModel, searchUserGroupViewModel);

        SearchGroupInputBoundary searchInteractor = new SearchGroupInteractor(searchGroupOutputBoundary, searchGroupUserDataAccess);

        return new SearchUserGroupController(searchInteractor);
    }
}
