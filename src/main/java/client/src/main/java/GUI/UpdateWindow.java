package GUI;

import data.*;
import handler.connectionHandler;
import requests.InsertRequest;
import requests.UpdateRequest;
import statuses.OKStatus;
import statuses.Status;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UpdateWindow extends JDialog {
    private static final Color VALID_COLOR = Color.GREEN;
    private static final Color INVALID_COLOR = Color.RED;

    private connectionHandler connection;

    private boolean[] valideTicket = new boolean[6];
    private boolean venueOn = false;
    private boolean[] valideVenue = new boolean[2];
    private Ticket ticket = new Ticket();
    private String key;

    private JTextField[] leftFields = new JTextField[7];
    private JComboBox<String> leftComboBox;
    private JTextField[] rightFields = new JTextField[2];
    private JComboBox<String> rightComboBox;
    private JPanel rightPanel = new JPanel(new GridLayout(3, 2, 10, 10));

    // Маппинг между текстовыми полями и их названиями
    private Map<JTextField, String> fieldNames = new HashMap<>();

    public UpdateWindow(connectionHandler connectionHandler, JFrame parent, String key) {
        super(parent, "Update", true);
        this.connection = connectionHandler;
        this.key = key;
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2, 20, 0));

        JPanel leftPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        String[] leftLabels = {"Key:", "Name:", "X:", "Y:", "Price:", "Discount:", "Comment:"};
        for (int i = 0; i < leftFields.length; i++) {
            leftPanel.add(new JLabel(leftLabels[i]));
            if (i == 0) {
                leftFields[i] = new JTextField(key);
                leftFields[i].setEditable(false);
            } else {
                leftFields[i] = new JTextField(10);
            }
            leftPanel.add(leftFields[i]);
            fieldNames.put(leftFields[i], leftLabels[i]);
            final int index = i;
            leftFields[i].addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    validateField(leftFields[index], fieldNames.get(leftFields[index]));
                }
            });
        }

        leftComboBox = new JComboBox<>(new String[]{"", "USUAL", "BUDGETARY", "CHEAP"});
        leftPanel.add(new JLabel("Type:"));
        leftPanel.add(leftComboBox);


        leftComboBox.addActionListener(e -> {
            String q = leftComboBox.getSelectedItem().toString();
            if (q.isEmpty()) ticket.setType(null);
            else ticket.setType(TicketType.valueOf(q));
        });

        JCheckBox addVenueCheckBox = new JCheckBox("Add Venue?");
        addVenueCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean selected = addVenueCheckBox.isSelected();
                rightPanel.setVisible(selected);
                venueOn = selected;
                pack();
            }
        });

        rightPanel.setVisible(false);

        String[] rightLabels = {"Venue name:", "Venue capacity:", "Venue type:"};
        for (int i = 0; i < rightFields.length; i++) {
            rightPanel.add(new JLabel(rightLabels[i]));
            rightFields[i] = new JTextField(10);
            rightPanel.add(rightFields[i]);
            fieldNames.put(rightFields[i], rightLabels[i]);
            final int index = i;
            rightFields[i].addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    validateField(rightFields[index], fieldNames.get(rightFields[index]));
                }
            });
        }

        rightComboBox = new JComboBox<>(new String[]{"BAR", "LOFT", "OPEN_AREA", "THEATRE", "STADIUM"});
        rightPanel.add(new JLabel("Type:"));
        rightPanel.add(rightComboBox);

        rightComboBox.addActionListener(e -> {
            String q = rightComboBox.getSelectedItem().toString();
            if (q.isEmpty()) ticket.getVenue().setType(null);
            else ticket.getVenue().setType(VenueType.valueOf(q));
        });

        JButton insertButton = new JButton("Update");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean OK = true;
                for (int i = 0; i < valideTicket.length; i++) {
                    if (!valideTicket[i]) {
                        OK = false;
                    }
                }
                if (venueOn) {
                    for (int i = 0; i < valideVenue.length; i++) {
                        if (!valideVenue[i]) {
                            OK = false;
                        }
                    }
                }

                if (OK) {
                    try {
                        Status status = connection.sendCommand(new UpdateRequest(key, ticket));
                        if (status.getClass() == OKStatus.class) {
                            JOptionPane.showMessageDialog(null, "Successfully updated ticket!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error! Try again", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Other error! Try later", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect input!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(addVenueCheckBox, BorderLayout.NORTH);
        getContentPane().add(insertButton, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    private void validateField(JTextField field, String fieldName) {
        if (fieldName.equals("Name:")) {
            if (validateNew(field.getText(), false, null, null, false)) {
                field.setBackground(VALID_COLOR);
                valideTicket[0] = true;
                ticket.setName(field.getText());
            } else {
                field.setBackground(INVALID_COLOR);
                valideTicket[0] = false;
            }
        } else if (fieldName.equals("X:")) {
            if (validateNew(field.getText(), false, null, 766, false)) {
                field.setBackground(VALID_COLOR);
                valideTicket[1] = true;
                if (ticket.getCoordinates() == null) {
                    ticket.setCoordinates(new Coordinates());
                }
                ticket.getCoordinates().setX(Float.parseFloat(field.getText()));
            } else {
                field.setBackground(INVALID_COLOR);
                valideTicket[1] = false;
            }
        } else if (fieldName.equals("Y:")) {
            if (validateNew(field.getText(), false, null, null, false)) {
                field.setBackground(VALID_COLOR);
                valideTicket[2] = true;
                if (ticket.getCoordinates() == null) {
                    ticket.setCoordinates(new Coordinates());
                }
                ticket.getCoordinates().setY(Float.parseFloat(field.getText()));
            } else {
                field.setBackground(INVALID_COLOR);
                valideTicket[2] = false;
            }
        } else if (fieldName.equals("Price:")) {
            if (validateNew(field.getText(), false, 0, null, false)) {
                field.setBackground(VALID_COLOR);
                valideTicket[3] = true;
                ticket.setPrice(Float.parseFloat(field.getText()));
            } else {
                field.setBackground(INVALID_COLOR);
                valideTicket[3] = false;
            }
        } else if (fieldName.equals("Discount:")) {
            if (validateNew(field.getText(), false, 0, 100, false)) {
                field.setBackground(VALID_COLOR);
                valideTicket[4] = true;
                ticket.setDiscount(Integer.parseInt(field.getText()));
            } else {
                field.setBackground(INVALID_COLOR);
                valideTicket[4] = false;
            }
        } else if (fieldName.equals("Comment:")) {
            if (validateNew(field.getText(), true, null, null, true)) {
                field.setBackground(VALID_COLOR);
                valideTicket[5] = true;
                ticket.setComment(field.getText());
            } else {
                field.setBackground(INVALID_COLOR);
                valideTicket[5] = false;
            }
        } else if (fieldName.equals("Key:")) {
            key = field.getText();
        }

        if (fieldName.equals("Venue name:")) {
            if (validateNew(field.getText(), false, null, null, false)) {
                field.setBackground(VALID_COLOR);
                valideVenue[0] = true;
                ticket.setVenue(new Venue());
                ticket.getVenue().setName(field.getText());
            } else {
                field.setBackground(INVALID_COLOR);
                valideVenue[0] = false;
            }
        } else if (fieldName.equals("Venue capacity:")) {
            if (validateNew(field.getText(), false, 0, null, false)) {
                field.setBackground(VALID_COLOR);
                valideVenue[1] = true;
                ticket.getVenue().setCapacity(Long.valueOf(field.getText()));
            } else {
                field.setBackground(INVALID_COLOR);
                valideVenue[1] = false;
            }
        }
    }

    protected boolean validateNew (String obj, boolean canBeNull, Integer moreThen, Integer lessThen,
                                   boolean canBeEmptyString) {
        if ((!canBeNull) && (obj == null)) return false;
        if (obj == null) return true;
        try {
            if ((moreThen != null) && (Double.parseDouble(obj) <= moreThen)) return false;
            if ((lessThen != null) && (Double.parseDouble(obj) >= lessThen)) return false;
        } catch (NumberFormatException e) {
            return false;
        }
        if ((!canBeEmptyString) && (Objects.equals(obj, ""))) return false;
        return true;
    }
}
