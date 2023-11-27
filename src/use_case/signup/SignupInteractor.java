package use_case.signup;

import entity.Group;
import entity.GroupFactory;
import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;


public class SignupInteractor implements SignupInputBoundary{

    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    final GroupFactory groupFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupUserDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory,
                            GroupFactory groupFactory) {
        this.userDataAccessObject = signupUserDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
        this.groupFactory = groupFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if(userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists");
        } else {
            LocalDateTime now = LocalDateTime.now();
            User user;
            if (userDataAccessObject.existsByGroup(signupInputData.getGroup())){
                user = userFactory.create(signupInputData.getUsername(), signupInputData.getLocation(),null,null);
                Group group = userDataAccessObject.getGroup(signupInputData.getGroup());
                group.addMember(user);
                userDataAccessObject.save(user,null);
            }else{
                user = userFactory.create(signupInputData.getUsername(), signupInputData.getLocation(),null,signupInputData.getGroup());
                Group group = groupFactory.create(signupInputData.getGroup(), user, now);
                userDataAccessObject.save(user,group);

            }


            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), now.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }



}
