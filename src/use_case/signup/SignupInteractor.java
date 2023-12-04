package use_case.signup;

import api.WeatherDB;
import entity.Group;
import entity.GroupFactory;
import entity.User;
import entity.UserFactory;
import use_case.group.GroupDataAccessInterface;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class SignupInteractor implements SignupInputBoundary{

    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    final GroupFactory groupFactory;

    final GroupDataAccessInterface groupDataAccessObject;

    final WeatherDB weatherDataAccsssObject;

    public SignupInteractor(SignupUserDataAccessInterface signupUserDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory,
                            GroupFactory groupFactory,
                            GroupDataAccessInterface groupDataAccessObject,
                            WeatherDB weatherDataAccsssObject) {
        this.userDataAccessObject = signupUserDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
        this.groupFactory = groupFactory;
        this.groupDataAccessObject = groupDataAccessObject;
        this.weatherDataAccsssObject = weatherDataAccsssObject;
    }

    @Override
    public void execute(SignupInputData signupInputData) {

        if(userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists");
        } else if (groupDataAccessObject.existsByName(signupInputData.getGroup()) && signupInputData.isNewGroup()) {
            userPresenter.prepareFailView("Group already exists");
        } else if (!groupDataAccessObject.existsByName(signupInputData.getGroup()) && !signupInputData.isNewGroup()) {
            //
            System.out.println(signupInputData.isNewGroup());
            userPresenter.prepareFailView("Group does not exist");
        }
        else{
            try {
                System.out.println(signupInputData.getLocation());
                weatherDataAccsssObject.getWeather(signupInputData.getLocation());
                User user = userFactory.create(signupInputData.getUsername(), signupInputData.getLocation(), null, signupInputData.getGroup());
                if (!signupInputData.getGroup().equals("")){
                    if (signupInputData.isNewGroup()) {
                        Group group = groupFactory.create(signupInputData.getGroup(), new ArrayList<>(), LocalDateTime.now());
                        group.addMember(user);
                        groupDataAccessObject.save(signupInputData.getGroup(), group);
                    } else {
                        groupDataAccessObject.addMember(signupInputData.getGroup(), user);
                        groupDataAccessObject.save(signupInputData.getGroup(), null);
                    }
                }
                userDataAccessObject.save(user);
                SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), LocalDateTime.now().toString(), signupInputData.getGroup(), false);
                userPresenter.prepareSuccessView(signupOutputData);
            }catch (Exception e){
                userPresenter.prepareFailView("invalid location");
            }
        }
        }
    }



