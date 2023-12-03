package view;

import interface_adapter.trip.TripController;
import interface_adapter.trip.TripState;
import interface_adapter.trip.TripViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TripView extends JPanel implements ActionListener, PropertyChangeListener {
    private final TripController controller;
    private final TripViewModel viewModel;

    private final JTextField tripNameInputField = new JTextField(15);
    private final JTextField tripIdInputField = new JTextField(15);
    private final JButton createTripButton;

    public TripView(TripController controller, TripViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(TripViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel tripNameInfo = new LabelTextPanel(
                new JLabel(TripViewModel.TRIP_NAME_LABEL), tripNameInputField);

        LabelTextPanel tripIdInfo = new LabelTextPanel(
                new JLabel(TripViewModel.TRIP_ID_LABEL), tripIdInputField);

        JPanel buttons = new JPanel();
        createTripButton = new JButton(TripViewModel.CREATE_TRIP_BUTTON_LABEL);
        buttons.add(createTripButton);

        createTripButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(createTripButton)) {
                    TripState currentState = viewModel.getState();
                    // You might want to generate the trip ID automatically or let the user enter it.
                    // Here we assume the ID is entered by the user.
                    controller.createTrip(currentState.getTripId());
                }
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(tripNameInfo);
        this.add(tripIdInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle actions if necessary
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            TripState state = (TripState) evt.getNewValue();
            if (state.getError() != null) {
                JOptionPane.showMessageDialog(this, state.getError());
            }
        }
    }
}
