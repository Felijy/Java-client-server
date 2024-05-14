package commandsHendler.commands;

import commandsHendler.Command;
import handler.connectionHandler;
import requests.InfoRequest;
import statuses.Status;

import java.io.IOException;

/**
 * Команда для вывода основной информации о коллекции
 */
public class Info extends Command {
    public Info() {
        super(false);
    }

    @Override
    public Status execute(String args, connectionHandler connectionHandler) throws IOException {
        var request = new InfoRequest();
        return connectionHandler.sendCommand(request);
    }

    @Override
    public String getDescription() {
        return "выводит основную информацию о коллекции";
    }

    @Override
    public String getName() {
        return "info";
    }
}
