package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GreetingsWindow extends JFrame {

    private JLabel greetingLabel;
    private int i;
    private Timer timer;
    private String[] greetings = {"Привет!", "Hello!", "Good afternoon!", "Olá!", "Boa tarde!", "Здравей!", "Добър ден!", "¡Hola!", "¡Buenas tardes!"};
    private int currentX = 10;

    public GreetingsWindow() {
        setTitle("Welcome");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JComboBox<String> languageComboBox = new JComboBox<>(new String[]{"", "English", "Русский", "Português", "български", "Español"});
        languageComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                String selectedLanguage = (String) comboBox.getSelectedItem();
                updateGreeting(selectedLanguage);
                selectedLanguage = switch (selectedLanguage) {
                    case "Русский" -> "ru";
                    case "Português" -> "po";
                    case "български" -> "bo";
                    case "Español" -> "es";
                    default -> "en";
                };
                Lang.setLang(selectedLanguage);
            }
        });
        JPanel greetingPanel = new JPanel(new BorderLayout());
        greetingPanel.add(languageComboBox, BorderLayout.NORTH);
        JButton startButton = new JButton(Lang.LoginWindow.getNext());
        greetingPanel.add(startButton, BorderLayout.SOUTH);
        add(greetingPanel, BorderLayout.SOUTH);

        // Создание JLabel для приветствия
        greetingLabel = new JLabel();
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        i = 0;
        greetingLabel.setText(greetings[i]);
        add(greetingLabel, BorderLayout.CENTER);

        // Начать анимацию
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentX--;
                greetingLabel.setLocation(currentX, 10);

                if (currentX + greetingLabel.getWidth() < 0) {
                    currentX = getWidth();
                    int nextIndex = ++i;
                    if (nextIndex >= greetings.length) {
                        nextIndex = 0;
                        i = 0;
                    }
                    greetingLabel.setText(greetings[nextIndex]);
                }
            }
        });
        timer.start();

        startButton.addActionListener(e -> {
            dispose();
            ConnectingWindow window = new ConnectingWindow();
        });
    }

    private void updateGreeting(String language) {
        switch (language) {
            //"", "English", "Русский", "Português", "български", "Español"
            case "Русский":
                greetings = new String[]{"Привет!", "Здравствуйте!", "Добрый день!"};
                break;
            case "English":
                greetings = new String[]{"Hello!", "Good afternoon!"};
                break;
            case "Português":
                greetings = new String[]{"Olá!", "Boa tarde!"};
                break;
            case "български":
                greetings = new String[]{"Здравей!", "Добър ден!"};
                break;
            case "Español":
                greetings = new String[]{"¡Hola!", "¡Buenas tardes!"};
                break;
            default:
                greetings = new String[]{"Привет!", "Hello!", "Good afternoon!", "Olá!", "Boa tarde!", "Здравей!", "Добър ден!", "¡Hola!", "¡Buenas tardes!"};
        }
        currentX = getWidth(); // Сбросить положение текста
        greetingLabel.setText(greetings[0]); // Обновить текст
    }

}
