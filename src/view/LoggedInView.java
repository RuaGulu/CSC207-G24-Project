package view;

import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName = "logged in";

    private final LoggedInViewModel loggedInViewModel;

    JLabel username;

    JLabel location;

    final JButton weather;


    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);


        JLabel title = new JLabel("LoggedInScreen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel usernameInfo = new JLabel("Username:");
        username = new JLabel();
        JLabel locationInfo = new JLabel("Location:");
        location = new JLabel();

        JPanel buttons = new JPanel();
        weather = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(weather);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(username);
        this.add(location);
        this.add(buttons);
        // add the weather panel
        // add the groups panel
            // this means the groups are stored somewhere, will need to call them to display
            // not sure how groups are stored yet, so haven't coded yet
            // likely called in input data or login presenter -unsure right now



    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        username.setText(state.getUsername());
        location.setText(state.getLocation());


    }
}
