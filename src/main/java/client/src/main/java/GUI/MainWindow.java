package GUI;

import data.Ticket;
import handler.connectionHandler;
import handler.userHandler;
import information.ShowInfo;
import requests.ShowRequest;
import statuses.OKResponseStatus;
import statuses.Status;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MainWindow extends JFrame {

    private String login;
    private String password;
    private connectionHandler connection;

    public MainWindow() {
        this.connection = userHandler.getConnectionHandler();
        this.login = userHandler.getLogin();
        this.password = userHandler.getPassword();
        try {
            createAndShowGUI();
        } catch (IOException e) {
            ConnectingWindow connectingWindow = new ConnectingWindow();
        }
    }

    private void createAndShowGUI() throws IOException {
        JFrame frame = new JFrame(Lang.MainWindow.getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());


        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(Lang.MainWindow.getTitle(), JLabel.CENTER);
        JButton visualizationButton = new JButton(Lang.MainWindow.getVisualization());
        JButton exitButton = new JButton(Lang.MainWindow.getExit());
        JComboBox<String> changeLanguageButton = new JComboBox<>(new String[]{Lang.MainWindow.getLanguage(), "English", "Русский", "Português", "български", "Español"});
        JLabel currentUserLabel = new JLabel(Lang.MainWindow.getUser() + login, JLabel.RIGHT);

        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(visualizationButton, BorderLayout.WEST);
        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topRightPanel.add(changeLanguageButton);
        topRightPanel.add(exitButton);
        topRightPanel.add(currentUserLabel);
        topPanel.add(topRightPanel, BorderLayout.EAST);

        pane.add(topPanel, BorderLayout.NORTH);


        JPanel sortFilterPanel = new JPanel();
        sortFilterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel filterPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel filterPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel sortByLabel = new JLabel(Lang.MainWindow.getSort());
        JComboBox<String> sortByComboBox = new JComboBox<>(new String[]{"", "key", "id", "name", "x", "y", "date", "price", "discount", "comment", "type", "venue_id",
                "venue_name", "venue_capacity", "venue_type", "user"});
        JComboBox<String> sortHowComboBox = new JComboBox<>(new String[]{"", Lang.MainWindow.getSortHow1(), Lang.MainWindow.getSortHow2()});
        JLabel filterByLabel = new JLabel(Lang.MainWindow.getFilter());
        JComboBox<String> filterByComboBox = new JComboBox<>(new String[]{"", "key", "id", "name", "x", "y", "date", "price", "discount", "comment", "type", "venue_id",
                "venue_name", "venue_capacity", "venue_type", "user"});
        JLabel filterLabel = new JLabel();
        JTextField filterTextField = new JTextField(10);

        filterPanelLeft.add(sortByLabel);
        filterPanelLeft.add(sortByComboBox);
        filterPanelLeft.add(sortHowComboBox);


        filterPanelRight.add(filterByLabel);
        filterPanelRight.add(filterByComboBox);
        filterPanelRight.add(filterLabel);
        filterPanelRight.add(filterTextField);

        JPanel filterPanel = new JPanel(new BorderLayout());
        filterPanel.add(filterPanelLeft, BorderLayout.WEST);
        filterPanel.add(filterPanelRight, BorderLayout.EAST);

        String[] columnNames = {"Key", "ID", "Name", "X", "Y", "Date", "Price", "Discount", "Comment", "Type", "Venue ID", "Venue Name", "Venue Capacity", "Venue Type", "User"};

        DefaultTableModel model = makeTable(columnNames,
                (String) filterByComboBox.getSelectedItem(),
                filterTextField.getText(),
                (String) sortByComboBox.getSelectedItem(),
                (String) sortHowComboBox.getSelectedItem());
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        filterPanel.add(scrollPane, BorderLayout.SOUTH);

        pane.add(filterPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        JButton insertButton = new JButton(Lang.MainWindow.getInsert());
        JButton refreshButton = new JButton(Lang.MainWindow.getRefresh());
        JButton otherCommandsButton = new JButton(Lang.MainWindow.getOther());

        bottomPanel.add(insertButton);
        bottomPanel.add(refreshButton);
        bottomPanel.add(otherCommandsButton);

        pane.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        filterByComboBox.addActionListener(e -> {
            model.setRowCount(0);
            try {
                DefaultTableModel newTable = makeTable(columnNames,
                        (String) filterByComboBox.getSelectedItem(),
                        filterTextField.getText(),
                        (String) sortByComboBox.getSelectedItem(),
                        (String) sortHowComboBox.getSelectedItem());
                table.setModel(newTable);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                frame.dispose();
                ConnectingWindow connectingWindow = new ConnectingWindow();
            }
        });

        filterTextField.addActionListener(e -> {
            model.setRowCount(0);
            try {
                DefaultTableModel newTable = makeTable(columnNames,
                        (String) filterByComboBox.getSelectedItem(),
                        filterTextField.getText(),
                        (String) sortByComboBox.getSelectedItem(),
                        (String) sortHowComboBox.getSelectedItem());
                table.setModel(newTable);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                frame.dispose();
                ConnectingWindow connectingWindow = new ConnectingWindow();
            }
        });

        sortByComboBox.addActionListener(e -> {
            model.setRowCount(0);
            try {
                DefaultTableModel newTable = makeTable(columnNames,
                        (String) filterByComboBox.getSelectedItem(),
                        filterTextField.getText(),
                        (String) sortByComboBox.getSelectedItem(),
                        (String) sortHowComboBox.getSelectedItem());
                table.setModel(newTable);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                frame.dispose();
                ConnectingWindow connectingWindow = new ConnectingWindow();
            }
        });

        sortHowComboBox.addActionListener(e -> {
            model.setRowCount(0);
            try {
                DefaultTableModel newTable = makeTable(columnNames,
                        (String) filterByComboBox.getSelectedItem(),
                        filterTextField.getText(),
                        (String) sortByComboBox.getSelectedItem(),
                        (String) sortHowComboBox.getSelectedItem());
                table.setModel(newTable);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                frame.dispose();
                ConnectingWindow connectingWindow = new ConnectingWindow();
            }
        });

        visualizationButton.addActionListener(e -> {
            frame.dispose();
            VisualWindow window = new VisualWindow(connection);
        });

        otherCommandsButton.addActionListener(e -> {
            OtherWindow otherWindow = new OtherWindow(connection, (JFrame) SwingUtilities.getWindowAncestor(this));
            model.setRowCount(0);
            try {
                DefaultTableModel newTable = makeTable(columnNames,
                        (String) filterByComboBox.getSelectedItem(),
                        filterTextField.getText(),
                        (String) sortByComboBox.getSelectedItem(),
                        (String) sortHowComboBox.getSelectedItem());
                table.setModel(newTable);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                frame.dispose();
                ConnectingWindow connectingWindow = new ConnectingWindow();
            }
        });

        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, Lang.MainWindow.getExitDone(), Lang.MainWindow.getExit(), JOptionPane.WARNING_MESSAGE);
            frame.dispose();
            userHandler.setLogin(null);
            userHandler.setPassword(null);
            ConnectingWindow cw = new ConnectingWindow();
        });

        insertButton.addActionListener(e -> {
            InsertWindow insertWindow = new InsertWindow(connection, (JFrame) SwingUtilities.getWindowAncestor(this));
            model.setRowCount(0);
            try {
                DefaultTableModel newTable = makeTable(columnNames,
                        (String) filterByComboBox.getSelectedItem(),
                        filterTextField.getText(),
                        (String) sortByComboBox.getSelectedItem(),
                        (String) sortHowComboBox.getSelectedItem());
                table.setModel(newTable);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                frame.dispose();
                ConnectingWindow connectingWindow = new ConnectingWindow();
            }
        });

        changeLanguageButton.addActionListener(e -> {
            String lang = (String) changeLanguageButton.getSelectedItem();
            lang = switch (lang) {
                case "Русский" -> "ru";
                case "Português" -> "po";
                case "български" -> "bo";
                case "Español" -> "es";
                default -> "en";
            };
            Lang.setLang(lang);
            MainWindow newWindow = new MainWindow();
            frame.dispose();
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                try {
                    DefaultTableModel newTable = makeTable(columnNames,
                            (String) filterByComboBox.getSelectedItem(),
                            filterTextField.getText(),
                            (String) sortByComboBox.getSelectedItem(),
                            (String) sortHowComboBox.getSelectedItem());
                    table.setModel(newTable);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), Lang.MainWindow.getError(), JOptionPane.ERROR_MESSAGE);
                    frame.dispose();
                    ConnectingWindow connectingWindow = new ConnectingWindow();
                }
                JOptionPane.showMessageDialog(null, Lang.MainWindow.getRefresh(), Lang.MainWindow.getRefresh(), JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }

    private DefaultTableModel makeTable(String[] columnNames, String filterBy, String filterText, String sortBy, String sortHow) throws IOException {
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Status status = connection.sendCommand(new ShowRequest());
        if (status.getClass() == OKResponseStatus.class) {
            ShowInfo showInfo = (ShowInfo) ((OKResponseStatus) status).getInfo();
            Map<String, Ticket> map = showInfo.getRes();

            java.util.List<String> keys = new ArrayList<>(map.keySet());

            if (!filterBy.isEmpty() && !filterText.isEmpty()) {
                keys = keys.stream()
                        .filter(key -> {
                            Ticket ticket = map.get(key);
                            if (ticket == null) return false;
                            String value = getValueForTicket(ticket, filterBy);
                            return filterText.equals(value);
                        })
                        .collect(Collectors.toList());
            }

            if (!sortBy.isEmpty() && !sortHow.isEmpty()) {
                Comparator<String> comparator = Comparator.comparing(key -> {
                    Ticket ticket = map.get(key);
                    return getValueForTicket(ticket, sortBy);
                });
                if (sortHow.equals(Lang.MainWindow.getSortHow2())) {
                    comparator = comparator.reversed();
                }
                keys.sort(comparator);
            }

            keys.forEach(key -> {
                Ticket ticket = map.get(key);
                Object[] rowData = {
                        key, ticket.getId(), ticket.getName(), ticket.getCoordinates().getX(), ticket.getCoordinates().getY(),
                        ticket.getCreationDate(), ticket.getPrice(), ticket.getDiscount(), ticket.getComment(), ticket.getType(),
                        (ticket.getVenue() != null) ? ticket.getVenue().getId() : null,
                        (ticket.getVenue() != null) ? ticket.getVenue().getName() : null,
                        (ticket.getVenue() != null) ? ticket.getVenue().getCapacity() : null,
                        (ticket.getVenue() != null) ? ticket.getVenue().getType() : null,
                        ticket.getUser()
                };
                model.addRow(rowData);
            });
        }
        return model;
    }


    private String getValueForTicket(Ticket ticket, String columnName) {
        if (ticket == null) {
            return "";
        }

        return switch (columnName) {
            case "id" -> String.valueOf(ticket.getId());
            case "name" -> ticket.getName();
            case "x" -> String.valueOf(ticket.getCoordinates().getX());
            case "y" -> String.valueOf(ticket.getCoordinates().getY());
            case "date" -> ticket.getCreationDate().toString();
            case "price" -> String.valueOf(ticket.getPrice());
            case "discount" -> String.valueOf(ticket.getDiscount());
            case "comment" -> ticket.getComment();
            case "type" -> {
                if (ticket.getType() == null) yield "";
                yield ticket.getType().name();
            }
            case "venue_id" -> (ticket.getVenue() != null) ? String.valueOf(ticket.getVenue().getId()) : "";
            case "venue_name" -> (ticket.getVenue() != null) ? ticket.getVenue().getName() : "";
            case "venue_capacity" ->
                    (ticket.getVenue() != null && ticket.getVenue().getType() != null) ? String.valueOf(ticket.getVenue().getCapacity()) : "";
            case "venue_type" -> (ticket.getVenue() != null) ? ticket.getVenue().getType().name() : "";
            case "user" -> ticket.getUser();
            default -> "";
        };
    }



}
