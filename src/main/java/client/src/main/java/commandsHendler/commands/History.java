package commandsHendler.commands;

import commandsHendler.Command;
import handler.CommandHandler;
import handler.connectionHandler;
import statuses.OKStatus;
import statuses.Status;

import java.io.IOException;

/**
 * Команда для вывода истории -- последних 15 выполненных команд
 */
public class History extends Command {
    public History() {
        super(false);
    }

    @Override
    public Status execute(String args, connectionHandler connectionHandler) throws IOException {
        CommandHandler.printHistory(15);
        return new OKStatus();
    }

    @Override
    public String getDescription() {
        return "выводит последние 15 команд";
    }

    @Override
    public String getName() {
        return "history";
    }
}
