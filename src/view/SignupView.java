package view;

import interface_adapter.logged_in.LoggedInState;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";

    private final SignupViewModel signupViewModel;

    private final LoginViewModel loginViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JTextField locationInputField = new JTextField(15);

    private final JTextField groupInputField = new JTextField(15);

    private final SignupController signupController;

    private final LoginController loginController;

    private final JButton signUp;

    private final JButton logIn;

    public SignupView(SignupController controller, SignupViewModel signupViewModel, LoginController loginController, LoginViewModel loginViewModel) {

        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(this);

        this.loginController = loginController;
        this.loginViewModel = loginViewModel;
        loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel locationInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.LOCATION_LABEL), locationInputField);

        // buttons
        JPanel buttons = new JPanel();
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        signUp.setPreferredSize(new Dimension(80,30));
        signUp.setContentAreaFilled(false);

        logIn = new JButton(LoginViewModel.TITLE_LABEL);



        buttons.add(signUp);
        buttons.add(logIn);

        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            SignupState currentState = signupViewModel.getState();
                            signupController.execute(
                                    currentState.getUsername(),
                                    currentState.getLocation(),
                                    currentState.getGroup()
                            );

                        }
                    }
                }
        );

        logIn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SignupState lastState = signupViewModel.getState();
                        LoginState currentState = loginViewModel.getState();
                        //
                        currentState.setUsername(lastState.getUsername());
                        currentState.setLocation(lastState.getLocation());
                        loginController.execute(currentState.getUsername(),null, "log in");


                    }
                }
        );

        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        signupViewModel.setState(currentState);

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        locationInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = locationInputField.getText() + e.getKeyChar();
                        currentState.setLocation(text);
                        signupViewModel.setState(currentState);

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        groupInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = groupInputField.getText() + e.getKeyChar();
                        currentState.setGroup(text);
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        this.add(title);
        this.add(usernameInfo);
        this.add(locationInfo);
        this.add(buttons);}

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getPropertyName()+"1");
        if (evt.getPropertyName().equals("logged in")) {
            LoggedInState state = (LoggedInState) evt.getNewValue();
//            if (state.getUsernameError() != null) {
//                JOptionPane.showMessageDialog(this, state.getUsernameError());

        } else if (evt.getPropertyName().equals("login")) {
                LoginState loginstate = (LoginState) evt.getNewValue();
                if (loginstate.getUsernameError() != null) {
                    JOptionPane.showMessageDialog(this, loginstate.getUsernameError());
            }}
    }
}

//
//
//        if (LoginState.class.isInstance(evt.getNewValue())){
//            System.out.println("这里");
//            LoginState state = (LoginState) evt.getNewValue();
//            if (state.getUsernameError() != null) {
//                JOptionPane.showMessageDialog(this, state.getUsernameError());
//            }
//        }else{
//            SignupState state = (SignupState) evt.getNewValue();
//            if (state.getUsernameError() != null) {
//                JOptionPane.showMessageDialog(this, state.getUsernameError());
//            }
//        }





