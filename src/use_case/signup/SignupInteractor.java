package use_case.signup;

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

    public SignupInteractor(SignupUserDataAccessInterface signupUserDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory,
                            GroupFactory groupFactory,
                            GroupDataAccessInterface groupDataAccessObject) {
        this.userDataAccessObject = signupUserDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
        this.groupFactory = groupFactory;
        this.groupDataAccessObject = groupDataAccessObject;
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
        } else {
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


//            if (userDataAccessObject.existsByGroup(signupInputData.getGroup()) && !signupInputData.isNewGroup()) {
//                user = userFactory.create(signupInputData.getUsername(), signupInputData.getLocation(),null,null);
//                Group group = userDataAccessObject.getGroup(signupInputData.getGroup());
//                group.addMember(user);
//                userDataAccessObject.save(user);
//            } else{
//                user = userFactory.create(signupInputData.getUsername(), signupInputData.getLocation(),null,signupInputData.getGroup());
//                Group group = groupFactory.create(signupInputData.getGroup(), user, now);
//                userDataAccessObject.save(user);


            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), LocalDateTime.now().toString(), signupInputData.getGroup(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
        }
    }



