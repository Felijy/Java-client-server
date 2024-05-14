package GUI;

import handler.connectionHandler;
import handler.terminalHandler;
import handler.userHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.time.LocalDateTime;

public class ConnectingWindow extends JFrame {

    private JFrame frame;

    public ConnectingWindow() {
        frame = new JFrame(Lang.Info.getConnectingTitle());
        frame.setSize(300, 100);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel messageLabel = new JLabel(Lang.Info.getConnectingText(), SwingConstants.CENTER);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(messageLabel, BorderLayout.NORTH);
        panel.add(progressBar, BorderLayout.CENTER);

        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());
        pane.add(panel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        retry();
    }

    public void retry() {
        try {
            connectionHandler connectionHandler = new connectionHandler();
            frame.dispose();
            userHandler.setConnectionHandler(connectionHandler);
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.start();
        } catch (IOException e) {
            retryConnection();
        }
    }


    public void retryConnection() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        retry();
    }
}