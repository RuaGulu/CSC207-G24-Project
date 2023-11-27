package view;

import entity.Weather;
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

public class LoggedinView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in";
    private final JTextField username = new JFormattedTextField(15);
    private final LoggedInViewModel viewModel;

    private final WeatherController weatherController;
    private final WeatherViewModel weatherViewModel;

//    private final GroupController groupController;
//    private final GroupViewModel groupViewModel;

    private final JTextField usernameInputField = new JTextField(15);

    private final JButton getWeather;

    private final JButton createGroup;




    public LoggedinView(LoggedInViewModel viewModel, WeatherController weatherController, WeatherViewModel weatherViewModel){
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        this.weatherController = weatherController;
        this.weatherViewModel = weatherViewModel;

//        this.groupViewModel = groupViewModel;
//        this.groupController = groupController;

        JLabel title = new JLabel(viewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        LabelTextPanel usernameInfo = new LabelTextPanel(new JLabel(), usernameInputField);
        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        WeatherState currentState = weatherViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setLocation(text);
                        weatherViewModel.setState(currentState);

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
        getWeather = new JButton("Get Weather");
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

        createGroup = new JButton("Group");
        button.add(createGroup);
        createGroup.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
//                        GroupState state = groupViewModel.getState();
//                        groupController.execute(state);
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
        //
        System.out.println("到达loggedinview");
        System.out.println(evt.getPropertyName());
        WeatherState state = weatherViewModel.getState();
        System.out.println(state.getWeather());

        if (state.getWeather() != null){
            Weather weather = state.getWeather();
            String str = "Weather Condition: " + weather.getCondition() + "\t"
                    + "Weather Temperature (C)" + weather.getTempC() + "\t"
                    + "Weather Temperature (F)"+ weather.getTempF() + "\t"
                    + "Local Time" + weather.getTime();

            JOptionPane.showMessageDialog(this, str);
    }
}}

