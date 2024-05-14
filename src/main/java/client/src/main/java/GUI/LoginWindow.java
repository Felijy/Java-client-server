package GUI;

import handler.connectionHandler;
import handler.userHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame{

    private connectionHandler connection;

    private JLabel welcomeLabel;
    private JLabel instructionLabel;
    private JTextField loginField;
    private JButton nextButton;

    public LoginWindow() {
        this.connection = userHandler.getConnectionHandler();
    }

    public void start() {
        setTitle(Lang.LoginWindow.getTitle());
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        welcomeLabel = new JLabel(Lang.LoginWindow.getWelcome());
        instructionLabel = new JLabel(Lang.LoginWindow.getLogin());
        loginField = new JTextField();
        nextButton = new JButton(Lang.LoginWindow.getNext());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        panel.add(welcomeLabel);
        panel.add(instructionLabel);
        panel.add(loginField);
        panel.add(nextButton);

        nextButton.addActionListener(e -> {
            String login = loginField.getText();
            userHandler.setLogin(login);
            dispose();
            PasswordWindow passwordWindow = new PasswordWindow();
            passwordWindow.start();
        });

        if (userHandler.getLogin() != null) {
            PasswordWindow passwordWindow = new PasswordWindow();
            passwordWindow.start();
        }

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }
}
