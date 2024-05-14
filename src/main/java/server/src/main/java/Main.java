import commandsHendler.commands.*;
import data.Ticket;
import handler.*;
import commandsHendler.commands.Insert;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Основной класс, с которого начинается выполнение программы. Создает необходимые объекты классов и переходит в интерактивный режим
 */
public class Main {
    public static void main(String[] args) {
        Run run = new Run();
        run.startServer();
    }
}
