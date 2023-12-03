package view;

import entity.Weather;
import entity.AirQuality;
import interface_adapter.air_quality.AirQualityPresenter;
import interface_adapter.group.GroupController;
import interface_adapter.group.GroupState;
import interface_adapter.group.GroupViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.weather.WeatherController;
import interface_adapter.weather.WeatherState;
import interface_adapter.weather.WeatherViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import interface_adapter.air_quality.AirQualityController;
import interface_adapter.air_quality.AirQualityState;
import interface_adapter.air_quality.AirQualityViewModel;

public class LoggedinView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in";
    private final JTextField username = new JFormattedTextField(15);
    private final LoggedInViewModel viewModel;

    private final WeatherController weatherController;
    private final WeatherViewModel weatherViewModel;

    private final AirQualityController airQualityController;

    private final AirQualityViewModel airQualityViewModel;

    private final GroupController groupController;
    private final GroupViewModel groupViewModel;

    private final JTextField locationInputField = new JTextField(15);

    private final JButton getWeather;

    private final JButton group;
    private final JButton getAirQuality;




    public LoggedinView(LoggedInViewModel viewModel, WeatherController weatherController, WeatherViewModel weatherViewModel, AirQualityController airQualityController, AirQualityViewModel airQualityViewModel,GroupViewModel groupViewModel, GroupController groupController){
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        this.weatherController = weatherController;
        this.weatherViewModel = weatherViewModel;

        this.groupViewModel = groupViewModel;
        this.groupController = groupController;

        this.airQualityController = airQualityController;
        this.airQualityViewModel = airQualityViewModel;

        JLabel title = new JLabel(viewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        LabelTextPanel locationInfo = new LabelTextPanel(
                new JLabel(LoggedInViewModel.LOCATION_INPUT_FIELD), locationInputField);

        locationInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        WeatherState weatherState = weatherViewModel.getState();
                        AirQualityState airQualityState = airQualityViewModel.getState();
                        String text = locationInputField.getText() + e.getKeyChar();
                        weatherState.setLocation(text);
                        airQualityState.setLocation(text);
                        weatherViewModel.setState(weatherState);
                        airQualityViewModel.setState(airQualityState);

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        JPanel button = new JPanel();
        getWeather = new JButton(LoggedInViewModel.WEATHER_BUTTON);
        button.add(getWeather);
        getWeather.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        WeatherState currentState = weatherViewModel.getState();
                        System.out.println("Loggedin view" + currentState.getLocation());
                        weatherController.execute(currentState.getLocation());


                        }

                }
        );
        getAirQuality = new JButton(LoggedInViewModel.AIR_BUTTON);
        button.add(getAirQuality);
        getAirQuality.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt){
                        AirQualityState currentState = airQualityViewModel.getState();
                        airQualityController.execute(currentState.getLocation());
                    }
                });



        group = new JButton("Get Group");
        button.add(group);
        group.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        LoggedInState lastState = viewModel.getState();
                        GroupState state = groupViewModel.getState();
                        state.setUser(lastState.getUsername());
                        groupController.execute(state.getUser(),lastState.getLocation());
                    }
                }


        );



        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(locationInfo);
        this.add(button);
        this.setVisible(true);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //
        System.out.println("Arrived in loggedinview");
        System.out.println(evt.getPropertyName());
        System.out.println(evt.getNewValue().getClass());

        System.out.println("=====");
        LoggedInState loggedInState = viewModel.getState();
        if (loggedInState.getProperty() == "group"){
            GroupState state = groupViewModel.getState();
            HashMap<String, HashMap<String,Weather>> data = state.getData();
            //
            System.out.println(state.getData().size());
            String str = "";
            for (String groupName : data.keySet()){
                HashMap<String,Weather> users = data.get(groupName);
                str += "Group " + groupName + " : " + "\n";
                for(String user : users.keySet()){
                    str += user + " : " + users.get(user).getLocation() + "\n"
                            + "Weather Condition: " + users.get(user).getCondition() + "\n"
                            + "Weather Temperature (C)" + users.get(user).getTempC() + "\n"
                            + "Weather Temperature (F)"+ users.get(user).getTempF() + "\n"
                            + "Local Time" + users.get(user).getTime() + "\n";
                }
            }
            JOptionPane.showMessageDialog(this,str);
        }
        WeatherState state = weatherViewModel.getState();
        System.out.println(state.getWeather());

        if (loggedInState.getProperty() == "weather"){
            Weather weather = state.getWeather();
            String str = "Weather Condition: " + weather.getCondition() + "\n"
                    + "Weather Temperature (C): " + weather.getTempC() + "\n"
                    + "Weather Temperature (F): "+ weather.getTempF() + "\n"
                    + "Local Time: " + weather.getTime();

            JOptionPane.showMessageDialog(this, str);
        }
        AirQualityState airState = airQualityViewModel.getState();
        if (loggedInState.getProperty() == "airquality"){
            AirQuality airQuality = airState.getAirQuality();
            String str = "Carbon Monoxide (μg/m3): " + airQuality.getCo() + "\n"
                    + "Ozone (μg/m3): " + airQuality.getO3() + "\n"
                    + "Nitrogen Dioxide (μg/m3): " + airQuality.getNo2() + "\n"
                    + "Sulphur Dioxide (μg/m3): " + airQuality.getSo2() + "\n"
                    + "PM2.5 (μg/m3): " + airQuality.getPm2_5() + "\n"
                    + "PM10 (μg/m3): " + airQuality.getPm10();

            JOptionPane.showMessageDialog(this, str);
        }
}}

