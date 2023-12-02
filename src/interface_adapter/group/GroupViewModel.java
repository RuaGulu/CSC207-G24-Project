package interface_adapter.group;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GroupViewModel extends ViewModel {

    private GroupState state = new GroupState();

    public GroupViewModel() {super("group");}

    public void setState(GroupState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("logged in", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public GroupState getState() {
        return state;
    }
}
