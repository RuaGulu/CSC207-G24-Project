package use_case.group;

import api.WeatherDB;
import entity.*;
import use_case.logged_in.LoggedInUserDataAccessinterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;



public class GroupInteractor implements GroupInputBoundary {
    final GroupDataAccessInterface groupDataAccessObject;

    final GroupOutputBoundary groupPresenter;

    final GroupFactory groupFactory;

    final WeatherDB weatherDataAccessObject;

    final LoginUserDataAccessInterface loginUserDataAccessInterface;




    public GroupInteractor(GroupDataAccessInterface groupDataAccessObject, GroupOutputBoundary CreateGroupPresenter, GroupFactory groupFactory, WeatherDB weatherDataAccessObject, LoginUserDataAccessInterface loginUserDataAccessInterface) {
        this.groupDataAccessObject = groupDataAccessObject;
        this.groupPresenter = CreateGroupPresenter;
        this.groupFactory = groupFactory;
        this.weatherDataAccessObject = weatherDataAccessObject;
        this.loginUserDataAccessInterface = loginUserDataAccessInterface;
    }

    public void execute(GroupInputData groupInputData) {
        HashMap<String, HashMap<String,Weather>> result = new HashMap<>();
        String userName = groupInputData.getUser();
        ArrayList<Group> groups = groupDataAccessObject.getGroups(userName);
        //
        System.out.println("==============");
        System.out.println("groupInteractor");
        System.out.println(userName);
        System.out.println(groupDataAccessObject.getGroups(userName).size());
        for (int i = 0; i < groups.size(); i++) {
            ArrayList<User> group = groups.get(i).getMembers();
            HashMap<String,Weather> conditions = new HashMap<>();
            for (int j = 0; j < group.size(); j++) {
                System.out.println(group.get(j).getUsername());
                User user = loginUserDataAccessInterface.get(group.get(j).getUsername());
                if (user != null){
                    //
                    System.out.println(user.getLocation());
                    conditions.put(group.get(j).getUsername(),weatherDataAccessObject.getWeather(user.getLocation()));
                }else{
                    //
                    System.out.println(groupInputData.getLocation());
                    conditions.put(group.get(j).getUsername(),weatherDataAccessObject.getWeather(groupInputData.getLocation()));
                }
            }
            result.put(groups.get(i).getName(),conditions);
        }

        GroupOutputData groupOutputData = new GroupOutputData(result);
        groupPresenter.prepareSuccessView(groupOutputData);

    }
}
