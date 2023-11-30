package view;


import entity.CommonGroup;
import entity.Group;
import interface_adapter.create_group.CreateGroupController;
import interface_adapter.create_group.CreateGroupState;
import interface_adapter.create_group.CreateGroupViewModel;
import interface_adapter.search_usergroup.SearchGroupState;
import interface_adapter.search_usergroup.SearchUserGroupController;
import interface_adapter.search_usergroup.SearchUserGroupViewModel;
import interface_adapter.signup.SignupState;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class GroupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "create group";
    private final CreateGroupViewModel creategroupViewModel;
    private final JTextField groupnameInputField = new JTextField(15);
    private final JLabel groupnameErrorField = new JLabel();
    private final JTextField userInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();
    private final JTextField groupSearchInputField = new JTextField(15);
    private final JLabel groupSearchErrorField = new JLabel();
    private final CreateGroupController creategroupController;
    // private final ClearGroupController clearController;
    private final SearchUserGroupController searchUserGroupController;
    private final SearchUserGroupViewModel searchUserGroupViewModel;

    private final JButton create;
    private final JButton cancel;
    LabelTextPanel groupnameInfo = new LabelTextPanel(
            new JLabel(CreateGroupViewModel.GROUPNAME_LABEL), groupnameInputField);

    LabelTextPanel usernameInfo = new LabelTextPanel(
            new JLabel(CreateGroupViewModel.USERNAME_LABEL), userInputField);
    // private final JButton delete;
    LabelTextPanel groupSearchInfo = new LabelTextPanel(
            new JLabel(CreateGroupViewModel.GROUPNAME_LABEL), groupnameInputField);
    public GroupView(CreateGroupController controller, SearchUserGroupController searchUserGroupController, CreateGroupViewModel creategroupViewModel, SearchUserGroupViewModel searchUserGroupViewModel) {

        this.creategroupController = controller;
        this.creategroupViewModel = creategroupViewModel;
        creategroupViewModel.addPropertyChangeListener(this);

        this.searchUserGroupController = searchUserGroupController;
        this.searchUserGroupViewModel = searchUserGroupViewModel;

        JLabel title = new JLabel(CreateGroupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel groupnameInfo = new LabelTextPanel(
                new JLabel(CreateGroupViewModel.GROUPNAME_LABEL), groupnameInputField);
        LabelTextPanel userInfo = new LabelTextPanel(
                new JLabel(CreateGroupViewModel.USERNAME_LABEL), userInputField);

        JPanel buttons = new JPanel();
        create = new JButton(CreateGroupViewModel.CREATE_BUTTON_LABEL);
        buttons.add(create);
        cancel = new JButton(CreateGroupViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        create.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(create)) {
                            CreateGroupState currentState = creategroupViewModel.getState();

                            creategroupController.execute(
                                    currentState.getGroupname(),
                                    currentState.getUser()
                            );
                            SearchGroupState searchState = searchUserGroupViewModel.getState();

                            searchUserGroupController.execute(
                                    searchState.getUsername()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener(this);

        groupnameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateGroupState currentState = creategroupViewModel.getState();
                        String text = groupnameInputField.getText() + e.getKeyChar();
                        currentState.setGroupname(text);
                        creategroupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        userInputField.addKeyListener(
            new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    CreateGroupState currentState = creategroupViewModel.getState();
                    String text = groupnameInputField.getText() + e.getKeyChar();
                    currentState.setGroupname(text);
                    creategroupViewModel.setState(currentState);
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
        this.add(groupnameInfo);
        this.add(groupnameErrorField);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("CreateGroupState")) {
            CreateGroupState state = (CreateGroupState) evt.getNewValue();
            SearchGroupState searchstate = (SearchGroupState) evt.getNewValue();
            if (state.getUserError() != null) {
                JOptionPane.showMessageDialog(this, state.getUserError());
            }
            else {
                List<Group> group = searchstate.getGroup();
                String str = "The user " + searchstate.getUsername() + " is currently in group " + group.toString();
                JOptionPane.showMessageDialog(this, str);
            }
        }
    }
}