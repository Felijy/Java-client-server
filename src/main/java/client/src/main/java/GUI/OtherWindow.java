package GUI;

import commandsHendler.commands.Execute;
import handler.CommandHandler;
import handler.connectionHandler;
import requests.*;
import statuses.OKResponseStatus;
import statuses.OKStatus;
import statuses.Status;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OtherWindow extends JDialog {

    private connectionHandler connection;
    // Словарь для хранения соответствия между кнопками и текстовыми полями
    private Map<JButton, JTextField> buttonTextFieldMap = new HashMap<>();

    public OtherWindow(connectionHandler connection, JFrame parent) {
        super(parent, Lang.MainWindow.getOther(), true);
        this.connection = connection;
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(5, 5));

        JLabel titleLabel = new JLabel(Lang.MainWindow.getOther(), JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        add(mainPanel, BorderLayout.CENTER);


        addCommand(mainPanel, Lang.OtherWindow.getClearTitle(), false, e -> {
            try {
                Status status = connection.sendCommand(new ClearRequest());
                if (status.getClass() == OKStatus.class) {
                    JOptionPane.showMessageDialog(mainPanel, Lang.OtherWindow.getClearText(), Lang.OtherWindow.getClearTitle(), JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(mainPanel, Lang.MainWindow.getError(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
            }
        });
        addCommand(mainPanel, Lang.OtherWindow.getHistoryTitle(), false, e -> {
            JOptionPane.showMessageDialog(this, CommandHandler.printHistory(15), Lang.OtherWindow.getHistoryTitle(), JOptionPane.INFORMATION_MESSAGE);
        });
        addCommand(mainPanel, Lang.OtherWindow.getInfoTitle(), false, e -> {
            try {
                Status status = connection.sendCommand(new InfoRequest());
                if (status.getClass() == OKResponseStatus.class) {
                    var q = ((OKResponseStatus) status).getInfo();
                    JOptionPane.showMessageDialog(this, q.printInfo(), Lang.OtherWindow.getInfoTitle(), JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(mainPanel, Lang.MainWindow.getError(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
            }
        });
        addCommand(mainPanel, Lang.OtherWindow.getMaxVenueTitle(), false, e -> {
            try {
                Status status = connection.sendCommand(new MaxVenueRequest());
                if (status.getClass() == OKResponseStatus.class) {
                    var q = ((OKResponseStatus) status).getInfo();
                    JOptionPane.showMessageDialog(this, q.printInfo(), Lang.OtherWindow.getMaxVenueTitle(), JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(mainPanel, Lang.MainWindow.getError(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
            }
        });

        addCommand(mainPanel, Lang.OtherWindow.getUpdateTitle(), true, e -> {
            JButton source = (JButton) e.getSource();
            JTextField textField = buttonTextFieldMap.get(source);
            if (textField != null) {
                String text = textField.getText();
                try {
                    Status status = connection.sendCommand(new CheckPermissionsRequest(text));
                    if (status.getClass() == OKStatus.class) {
                        UpdateWindow updateWindow = new UpdateWindow(connection, (JFrame) SwingUtilities.getWindowAncestor(getParent()), text);
                    } else
                        JOptionPane.showMessageDialog(mainPanel, Lang.OtherWindow.getForbiddenText(), Lang.OtherWindow.getForbiddenTitle(), JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, Lang.OtherWindow.getEnterAnArgumentText(), Lang.OtherWindow.getEnterAnArgumentTitile(), JOptionPane.WARNING_MESSAGE);
            }
        });


        addCommand(mainPanel, Lang.OtherWindow.getCountByPriveTitle(), true, e -> {
            JButton source = (JButton) e.getSource();
            JTextField textField = buttonTextFieldMap.get(source);
            if (!textField.getText().equals("")) {
                String text = textField.getText();
                try {
                    Status status = connection.sendCommand(new CountPriceRequest(text));
                    if (status.getClass() == OKResponseStatus.class) {
                        var q = ((OKResponseStatus) status).getInfo();
                        JOptionPane.showMessageDialog(this, q.printInfo(), Lang.OtherWindow.getCountByPriveTitle(), JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, Lang.MainWindow.getError(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, Lang.OtherWindow.getEnterAnArgumentText(), Lang.OtherWindow.getEnterAnArgumentTitile(), JOptionPane.WARNING_MESSAGE);
            }
        });


        addCommand(mainPanel, Lang.OtherWindow.getRemoveByKey(), true, e -> {
            JButton source = (JButton) e.getSource();
            JTextField textField = buttonTextFieldMap.get(source);
            if (!textField.getText().equals("")) {
                String text = textField.getText();
                try {
                    Status status = connection.sendCommand(new RemoveRequest(text));
                    if (status.getClass() == OKStatus.class) {
                        JOptionPane.showMessageDialog(this, Lang.InsertWindow.getSuccess(), Lang.InsertWindow.getSuccess(), JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, Lang.OtherWindow.getForbiddenText(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, Lang.OtherWindow.getEnterAnArgumentText(), Lang.OtherWindow.getEnterAnArgumentTitile(), JOptionPane.WARNING_MESSAGE);
            }
        });

        addCommand(mainPanel, Lang.OtherWindow.getRemoveByComment(), true, e -> {
            JButton source = (JButton) e.getSource();
            JTextField textField = buttonTextFieldMap.get(source);
            if (!textField.getText().equals("")) {
                String text = textField.getText();
                try {
                    Status status = connection.sendCommand(new RemoveByCommentRequest(text));
                    if (status.getClass() == OKStatus.class) {
                        JOptionPane.showMessageDialog(this, Lang.InsertWindow.getSuccess(), Lang.InsertWindow.getSuccess(), JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, Lang.OtherWindow.getForbiddenText(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, Lang.OtherWindow.getEnterAnArgumentText(), Lang.OtherWindow.getEnterAnArgumentTitile(), JOptionPane.WARNING_MESSAGE);
            }
        });

        addCommand(mainPanel, Lang.OtherWindow.getRemoveGraterID(), true, e -> {
            JButton source = (JButton) e.getSource();
            JTextField textField = buttonTextFieldMap.get(source);
            if (!textField.getText().equals("")) {
                String text = textField.getText();
                try {
                    Status status = connection.sendCommand(new RemoveGraterRequest(text));
                    if (status.getClass() == OKStatus.class) {
                        JOptionPane.showMessageDialog(this, Lang.InsertWindow.getSuccess(), Lang.InsertWindow.getSuccess(), JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, Lang.OtherWindow.getForbiddenText(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, Lang.OtherWindow.getEnterAnArgumentText(), Lang.OtherWindow.getEnterAnArgumentTitile(), JOptionPane.WARNING_MESSAGE);
            }
        });

        addCommand(mainPanel, Lang.OtherWindow.getRemoveGraterKey(), true, e -> {
            JButton source = (JButton) e.getSource();
            JTextField textField = buttonTextFieldMap.get(source);
            if (!textField.getText().equals("")) {
                String text = textField.getText();
                try {
                    Status status = connection.sendCommand(new RemoveGraterKeyRequest(text));
                    if (status.getClass() == OKStatus.class) {
                        JOptionPane.showMessageDialog(this, Lang.InsertWindow.getSuccess(), Lang.InsertWindow.getSuccess(), JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, Lang.OtherWindow.getForbiddenText(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, Lang.OtherWindow.getEnterAnArgumentText(), Lang.OtherWindow.getEnterAnArgumentTitile(), JOptionPane.WARNING_MESSAGE);
            }
        });

        setVisible(true);
    }

    private void addCommand(JPanel panel, String commandName, boolean hasTextField, ActionListener listener) {
        JPanel commandPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));

        JButton commandButton = new JButton(commandName);
        commandButton.setPreferredSize(new Dimension(200, 20));
        commandButton.addActionListener(listener);

        commandPanel.add(commandButton);

        if (hasTextField) {
            JTextField commandTextField = new JTextField(20); // Устанавливаем ширину текстового поля
            commandPanel.add(commandTextField);
            // Сохраняем соответствие кнопки и текстового поля
            buttonTextFieldMap.put(commandButton, commandTextField);
        }

        panel.add(commandPanel);
    }
}
