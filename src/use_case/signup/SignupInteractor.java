package use_case.signup;

import entity.CommonUser;
import entity.UserFactory;
import java.time.LocalDateTime;


public class SignupInteractor implements SignupInputBoundary{

    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupUserDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupUserDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if(userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists");
        } else {
            LocalDateTime now = LocalDateTime.now();
            CommonUser user = userFactory.create(signupInputData.getUsername(), signupInputData.getLocation(), now);
            userDataAccessObject.save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getName(), now.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }



}
