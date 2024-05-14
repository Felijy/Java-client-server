package GUI;

import handler.connectionHandler;
import handler.userHandler;
import requests.CheckPasswordRequest;
import requests.ExistUserRequest;
import requests.NewUserRequest;
import statuses.OKStatus;
import statuses.Status;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static handler.userHandler.hashPassword;

public class PasswordWindow extends JFrame {
    private JLabel existUserLabel;
    private JLabel instructionLabel;
    private JTextField passwordField;
    private JButton nextButton;

    private String login;
    private String password;
    private connectionHandler connection;

    public PasswordWindow() {
        this.login = userHandler.getLogin();
        this.connection = userHandler.getConnectionHandler();
    }

    public void start() {
        if (userHandler.getPassword() != null) {
            MainWindow mainWindow = new MainWindow();
        }
        Status status = null;
        try {
            status = connection.sendCommand(new ExistUserRequest(login));
            if (status.getClass() == OKStatus.class) {
                oldUser();
            } else {
                newUser();
            }
        } catch (IOException e) {
            dispose();
            ConnectingWindow connectingWindow = new ConnectingWindow();
        }
    }

    private void oldUser() {
        setTitle(Lang.LoginWindow.getTitle());
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        existUserLabel = new JLabel(Lang.PasswordWindow.getExist());
        instructionLabel = new JLabel(Lang.PasswordWindow.getEnterPass());
        passwordField = new JTextField();
        nextButton = new JButton(Lang.PasswordWindow.getSignIn());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        panel.add(existUserLabel);
        panel.add(instructionLabel);
        panel.add(passwordField);
        panel.add(nextButton);

        add(panel, BorderLayout.CENTER);
        setVisible(true);

        nextButton.addActionListener(e -> {
            password = passwordField.getText();
            password = hashPassword(password);
            Status status = null;
            try {
                status = connection.sendCommand(new CheckPasswordRequest(login, password));
            } catch (IOException ex) {
                dispose();
                ConnectingWindow connectingWindow = new ConnectingWindow();
            }
            if (status.getClass() == OKStatus.class) {
                JOptionPane.showMessageDialog(null, Lang.PasswordWindow.getSuccessfulSingIn(), Lang.InsertWindow.getSuccess(), JOptionPane.INFORMATION_MESSAGE);
                dispose();
                userHandler.setPassword(password);
                MainWindow mainWindow = new MainWindow();
            } else {
                JOptionPane.showMessageDialog(null, Lang.PasswordWindow.getInvalidPass(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                dispose();
                userHandler.setPassword(null);
                userHandler.setLogin(null);
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.start();
            }
        });
    }

    private void newUser() {
        setTitle(Lang.LoginWindow.getTitle());
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        existUserLabel = new JLabel(Lang.PasswordWindow.getNotExist());
        instructionLabel = new JLabel(Lang.PasswordWindow.getNewPass());
        passwordField = new JTextField();
        nextButton = new JButton(Lang.PasswordWindow.getSignIn());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        panel.add(existUserLabel);
        panel.add(instructionLabel);
        panel.add(passwordField);
        panel.add(nextButton);

        add(panel, BorderLayout.CENTER);
        setVisible(true);

        nextButton.addActionListener(e -> {
            password = passwordField.getText();
            password = hashPassword(password);
            Status status = null;
            try {
                status = connection.sendCommand(new NewUserRequest(login, password));
            } catch (IOException ex) {
                dispose();
                ConnectingWindow connectingWindow = new ConnectingWindow();
            }
            if (status.getClass() == OKStatus.class) {
                JOptionPane.showMessageDialog(null, Lang.PasswordWindow.getSuccessfulSingIn(), Lang.InsertWindow.getSuccess(), JOptionPane.INFORMATION_MESSAGE);
                userHandler.setPassword(password);
                dispose();
                MainWindow mainWindow = new MainWindow();
            } else {
                JOptionPane.showMessageDialog(null, Lang.MainWindow.getError(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                dispose();
                userHandler.setPassword(null);
                userHandler.setLogin(null);
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.start();
            }
        });
    }

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
