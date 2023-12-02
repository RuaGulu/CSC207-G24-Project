package view;

import entity.Weather;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.weather.WeatherController;
import interface_adapter.weather.WeatherState;
import interface_adapter.weather.WeatherViewModel;
import interface_adapter.air_quality.AirQualityController;
import interface_adapter.air_quality.AirQualityState;
import interface_adapter.air_quality.AirQualityViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedinView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in";
    private final JTextField username = new JFormattedTextField(15);
    private final LoggedInViewModel viewModel;

    private final WeatherController weatherController;
    private final WeatherViewModel weatherViewModel;

    private final AirQualityController airQualityController;

    private final AirQualityViewModel airQualityViewModel;

    private final JTextField weatherInputField = new JTextField(15);

    private final JButton getWeather;

    private final JButton getAirQuality;




    public LoggedinView(LoggedInViewModel viewModel, WeatherController weatherController, WeatherViewModel weatherViewModel,
                        AirQualityController airQualityController, AirQualityViewModel airQualityViewModel){
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        this.weatherController = weatherController;
        this.weatherViewModel = weatherViewModel;

        this.airQualityController = airQualityController;
        this.airQualityViewModel = airQualityViewModel;

        JLabel title = new JLabel(viewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        LabelTextPanel weatherInfo = new LabelTextPanel(new JLabel(LoggedInViewModel.WEATHER_LABEL), weatherInputField);

        // buttons
        JPanel buttons = new JPanel();



        JPanel button = new JPanel();
        getWeather = new JButton(LoggedInViewModel.WEATHER_BUTTON);

        getAirQuality = new JButton(LoggedInViewModel.AIR_BUTTON);

        buttons.add(getWeather);
        buttons.add(getAirQuality);

        getWeather.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(getWeather)) {
                            WeatherState currentState = weatherViewModel.getState();
                            weatherController.execute(currentState.getLocation());

                        }

                    }

                }
        );

        getAirQuality.addActionListener(

                new ActionListener() {
                public void actionPerformed(ActionEvent evt){
                    AirQualityState currentState = airQualityViewModel.getState();
                    airQualityController.execute(currentState.getLocation());
                }
            }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(weatherInfo);
        this.add(buttons);
        this.setVisible(true);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("到达loggedinview");
        if (evt.getNewValue().equals("loggedin")){
            WeatherState state = (WeatherState) evt.getNewValue();
            Weather weather = state.getWeather();
            String str = "Weather Condition: " + weather.getCondition() + "\n"
                    + "Weather Temperature (C)" + weather.getTempC() + "\n"
                    + "Weather Temperature (F)"+ weather.getTempF() + "\n"
                    + "Local Time" + weather.getTime();

            JOptionPane.showMessageDialog(this, str);
    }
}}

