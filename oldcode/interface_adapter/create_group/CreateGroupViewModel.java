package interface_adapter.create_group;

import interface_adapter.ViewModel;
import interface_adapter.signup.SignupState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateGroupViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Create your group";
    public static final String GROUPNAME_LABEL = "Enter group name";
    public static final String USERNAME_LABEL = "Choose username";
    public static final String CREATE_BUTTON_LABEL = "Create!";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private CreateGroupState state = new CreateGroupState();

    public CreateGroupViewModel() {super("create group");}

    public void setState(CreateGroupState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CreateGroupState getState() {
        return state;
    }
}
