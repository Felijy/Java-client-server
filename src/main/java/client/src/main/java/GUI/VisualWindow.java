package GUI;

import data.Coordinates;
import data.Ticket;
import handler.connectionHandler;
import information.ShowInfo;
import org.apache.commons.lang3.tuple.Pair;
import requests.ShowRequest;
import statuses.OKResponseStatus;
import statuses.Status;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class VisualWindow extends JFrame {
    private static final int WIDTH = 610;
    private static final int HEIGHT = 480;

    protected connectionHandler handler;

    public VisualWindow(connectionHandler connectionHandler) {
        super(Lang.MainWindow.getVisualization());
        handler = connectionHandler;
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        DrawingPanel panel = new DrawingPanel(handler);
        add(panel, BorderLayout.CENTER);

        JButton exitButton = new JButton("Back");
        add(exitButton, BorderLayout.SOUTH);

        setVisible(true);

        exitButton.addActionListener(e -> {
            dispose();
            MainWindow mainWindow = new MainWindow();
        });
    }
}

class DrawingPanel extends JPanel {
    private Image backgroundImage;
    private final int drawerX = 120;
    private final int drawerY = 80;
    private final int drawerWidth = 380;
    private final int drawerHeight = 220;
    private final int gridSpacing = 20;

    private connectionHandler connectionHandler;

    private HashMap<int[], Ticket> coordinates = new HashMap<>();

    public DrawingPanel(connectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
        backgroundImage = new ImageIcon("drawer.jpg").getImage();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, null);

        g.setColor(Color.BLACK);

        for (int i = 0; i < drawerHeight / gridSpacing; i++) {
            g.drawLine(drawerX, drawerY + i * gridSpacing, drawerX + drawerWidth, drawerY + i * gridSpacing);
        }

        for (int i = 0; i <= drawerWidth / gridSpacing; i++) {
            g.drawLine(drawerX + i * gridSpacing, drawerY, drawerX + i * gridSpacing, drawerY + drawerHeight);
        }

        g.drawLine(drawerX, drawerY + drawerHeight, drawerX + drawerWidth, drawerY + drawerHeight); // X-axis
        g.drawLine(drawerX, drawerY, drawerX, drawerY + drawerHeight); // Y-axis


        g.setColor(Color.RED);
        int squareSize = 20;

        Map<String, Ticket> map = null;
        try {
            Status status = connectionHandler.sendCommand(new ShowRequest());
            if (status.getClass() == OKResponseStatus.class) {
                ShowInfo showInfo = (ShowInfo) ((OKResponseStatus) status).getInfo();
                map = showInfo.getRes();
            } else {
                JOptionPane.showMessageDialog(null, Lang.MainWindow.getError(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
        }

        HashMap<String, Color> colors = new HashMap<>();


        for (int i = 0; i < drawerWidth / gridSpacing; i++) {
            for (int j = 0; j < drawerHeight / gridSpacing; j++) {

                java.util.List<String> keys = new ArrayList<>(map.keySet());

                for (String key : keys) {
                    Ticket ticket = map.get(key);
                    if (ticket.getCoordinates().getX() == i && ticket.getCoordinates().getY() == j) {
                        var keyColors = colors.keySet();
                        if (keyColors.contains(ticket.getUser())) {
                            g.setColor(colors.get(ticket.getUser()));
                        } else {
                            var randomColor = new Color((int)(Math.random() * 0x1000000));
                            randomColor.brighter();
                            colors.put(ticket.getUser(), randomColor);
                            g.setColor(randomColor);
                        }
                        int x = drawerX + i * gridSpacing - squareSize / 2;
                        int y = drawerY + drawerHeight - j * gridSpacing - squareSize / 2;
                        g.fillRect(x, y, squareSize, squareSize);
                        int[] q = new int[2];
                        q[0] = i;
                        q[1] = j;
                        coordinates.put(q, ticket);
                    }
                }
            }
        }
    }

    private void handleMouseClick(MouseEvent e) {
        int clickX = e.getX() - drawerX;
        int clickY = e.getY() - drawerY;

        int gridX = (int) Math.round((double) clickX / gridSpacing);
        int gridY = (int) Math.round((double) (drawerHeight - clickY) / gridSpacing);

        if (0 <= gridX && gridX < drawerWidth / gridSpacing && 0 <= gridY && gridY < drawerHeight / gridSpacing) {
            int[] q = new int[2];
            q[0] = gridX;
            q[1] = gridY;

            int[][] coordinatesArray = coordinates.keySet().toArray(new int[0][0]);

            for (int[] key : coordinatesArray) {
                if (key[0] == gridX && key[1] == gridY) {
                    processTicket(key);
                    return;
                }
            }
        }
    }

    private void processTicket(int[] q) {
        Ticket ticket = coordinates.get(q);
        if (ticket != null) {
            PictureWindow pictureWindow = new PictureWindow((JFrame) SwingUtilities.getWindowAncestor(this), ticket);
        }
    }


}
