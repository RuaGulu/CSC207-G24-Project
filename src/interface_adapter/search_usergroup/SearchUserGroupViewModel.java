package interface_adapter.search_usergroup;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchUserGroupViewModel extends ViewModel{
    private SearchGroupState state = new SearchGroupState();
    public SearchUserGroupViewModel() {
        super("search user's group");
    }
    public void setState(SearchGroupState state) {
        this.state = state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);}
    public void addPropertyChangeListener(PropertyChangeListener listener) {
            support.addPropertyChangeListener(listener);
        }
    public SearchGroupState getState() {
        return state;
    }
}

