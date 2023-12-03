package interface_adapter.signup;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupViewModel extends ViewModel{

    public static final String TITLE_LABEL = "Sign Up View";
    public static final String USERNAME_LABEL = "username ";
    public static final String LOCATION_LABEL = "location ";
    public static final String SIGNUP_BUTTON_LABEL = "Sign up ";

    public static final String SIGNUP_CREATE_GROUP_LABEL = "create group (optional) ";

    public static final String SIGNUP_JOIN_GROUP_LABEL = "join group (optional) ";



    private SignupState state = new SignupState();

    public SignupViewModel() {
        super("sign up");
    }

    public void setState(SignupState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("signup", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SignupState getState() {
        return state;
    }

}