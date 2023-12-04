package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "log in";
    private final JTextField username = new JFormattedTextField(15);
    private final LoginController controller;
    private final LoginViewModel viewModel;

    private final JTextField usernameInputField = new JTextField(15);

    private final JButton login;




    public LoginView(LoginController controller,LoginViewModel viewModel){
        this.controller = controller;
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(viewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        LabelTextPanel usernameInfo = new LabelTextPanel(new JLabel(viewModel.USERNAME_LABEL),usernameInputField);

        JPanel button = new JPanel();
        login = new JButton(viewModel.LOGIN_BUTTON);
        button.add(login);
        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState state = viewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        state.setKeyName(text);
                        viewModel.setState(state);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        login.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(login)) {
                            LoginState currentState = viewModel.getState();
                            controller.execute(currentState.getUsername(),currentState.getLocation(),currentState.getGroup(), "sign up",false, currentState.getKeyName());
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(usernameInfo);
        this.add(button);



    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //
        System.out.println("Arrived in login view");
        LoginState state = (LoginState) evt.getNewValue();
    }
}
