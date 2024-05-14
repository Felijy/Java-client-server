package commandsHendler.commands;

import commandsHendler.Command;
import handler.CommandHandler;
import handler.connectionHandler;
import requests.HelpRequest;
import statuses.Status;

import java.io.IOException;

/**
 * Команда для вывода справки
 */
public class Help extends Command {
    public Help() {
        super(false);
    }

    @Override
    public Status execute(String args, connectionHandler connectionHandler) throws IOException {
        var request = new HelpRequest();
        return connectionHandler.sendCommand(request);
    }

    @Override
    public String getDescription() {
        return "вывод справку по командам";
    }

    @Override
    public String getName() {
        return "help";
    }
}
