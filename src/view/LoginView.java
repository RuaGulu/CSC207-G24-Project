package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "log in";
    private final JTextField username = new JFormattedTextField(15);
    private final LoginController controller;
    private final LoginViewModel viewModel;

    private final JTextField usernameInputField = new JTextField(15);

    private final JButton loginIn;




    public LoginView(LoginController controller,LoginViewModel viewModel){
        this.controller = controller;
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(viewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        LabelTextPanel usernameInfo = new LabelTextPanel(new JLabel(viewModel.USERNAME_LABEL),usernameInputField);

        JPanel button = new JPanel();
        loginIn = new JButton(viewModel.LOGIN_BUTTON);
        button.add(loginIn);
        loginIn.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(loginIn)) {
                            LoginState currentState = viewModel.getState();
                            controller.execute(currentState.getUsername());
                        }
                    }
                }
        );



    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
