package interface_adapter.air_quality;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AirQualityViewModel extends ViewModel {
    public static final String AIR_QUALITY_BUTTON_LABEL = "Air Quality";

    private AirQualityState state = new AirQualityState();

    public AirQualityViewModel() {super("airQuality");}

    public void setState(AirQualityState state) {this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);}

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public AirQualityState getState() {
        return state;
    }
}