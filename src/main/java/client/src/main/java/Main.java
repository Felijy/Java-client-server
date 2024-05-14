import GUI.ConnectingWindow;
import GUI.GreetingsWindow;
import GUI.Lang;
import handler.Run;

import javax.swing.*;

/**
 * Основной класс, с которого начинается выполнение программы. Создает необходимые объекты классов и переходит в интерактивный режим
 */
public class Main {
    public static void main(String[] args) {
        Lang lang = new Lang();
        Run run = new Run();
//        run.startClient();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GreetingsWindow window = new GreetingsWindow();
                window.setVisible(true);
            }
        });

        //ticket t = new ticket();

        //MainWindow mainWindow = new MainWindow();

        //LoginWindow window = new LoginWindow();
        //window.start();
    }
}
