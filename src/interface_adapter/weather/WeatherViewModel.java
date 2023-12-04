package interface_adapter.weather;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WeatherViewModel extends ViewModel {
    public static final String WEATHER_BUTTON_LABEL = "Weather";

    private WeatherState state = new WeatherState();

    public WeatherViewModel() {
        super("weather");
    }

    public void setState(WeatherState state){
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public WeatherState getState(){
        return state;
    }
}
