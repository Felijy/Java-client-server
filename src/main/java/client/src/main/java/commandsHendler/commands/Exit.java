package commandsHendler.commands;

import commandsHendler.Command;
import handler.connectionHandler;
import statuses.Status;

import java.io.IOException;

/**
 * Команда для досрочного выхода из программы без сохранения данных
 */
public class Exit extends Command {
    public Exit() {
        super(false);
    }

    @Override
    public Status execute(String args, connectionHandler connectionHandler) throws IOException {
        System.exit(0);
        return null;
    }

    @Override
    public String getDescription() {
        return "выход без сохранения";
    }

    @Override
    public String getName() {
        return "exit";
    }
}
