package view;

import entity.Weather;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.weather.WeatherController;
import interface_adapter.weather.WeatherState;
import interface_adapter.weather.WeatherViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedinView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in";
    private final JTextField username = new JFormattedTextField(15);
    private final LoggedInController loggedinController;
    private final LoggedInViewModel viewModel;

    private final WeatherController weatherController;
    private final WeatherViewModel weatherViewModel;

    private final JTextField usernameInputField = new JTextField(15);

    private final JButton getWeather;




    public LoggedinView(LoggedInController controller, LoggedInViewModel viewModel, WeatherController weatherController, WeatherViewModel weatherViewModel){
        this.loggedinController = controller;
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        this.weatherController = weatherController;
        this.weatherViewModel = weatherViewModel;

        JLabel title = new JLabel(viewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        LabelTextPanel usernameInfo = new LabelTextPanel(new JLabel(), usernameInputField);

        JPanel button = new JPanel();
        getWeather = new JButton("Get Weather");
        button.add(getWeather);
        getWeather.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        WeatherState currentState = weatherViewModel.getState();
                        weatherController.execute(currentState.getUsername());



                        }

                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(usernameInfo);
        this.add(button);
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
            String str = "Weather Condition: " + weather.getCondition() + "\t"
                    + "Weather Temperature (C)" + weather.getTempC() + "\t"
                    + "Weather Temperature (F)"+ weather.getTempF() + "\t"
                    + "Local Time" + weather.getTime();

            JOptionPane.showMessageDialog(this, str);
    }
}}

