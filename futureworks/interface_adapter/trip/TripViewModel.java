package interface_adapter.trip;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TripViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Trip View";
    public static final String TRIP_NAME_LABEL = "Trip Name";
    public static final String TRIP_ID_LABEL = "Trip ID";
    public static final String CREATE_TRIP_BUTTON_LABEL = "Create Trip";

    private TripState state = new TripState();

    public TripViewModel() {
        super("trip");
    }

    public void setState(TripState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public TripState getState() {
        return state;
    }
}
