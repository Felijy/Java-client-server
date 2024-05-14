package commandsHendler.commands;

import commandsHendler.Command;

import handler.connectionHandler;
import requests.ShowRequest;
import statuses.Status;

import java.io.IOException;

/**
 * Команда для вывода всех элементов коллекции в строковом представлении
 */
public class Show extends Command {

    public Show() {
        super(false);
    }

    @Override
    public Status execute(String args, connectionHandler connectionHandler) throws IOException {
        var request = new ShowRequest();
        return connectionHandler.sendCommand(request);
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public String getName() {
        return "show";
    }
}
