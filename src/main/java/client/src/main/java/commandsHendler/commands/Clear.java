package commandsHendler.commands;

import commandsHendler.Command;
import handler.connectionHandler;
import requests.ClearRequest;
import statuses.Status;

import java.io.IOException;

/**
 * Команда для очистки коллекции
 */
public class Clear extends Command {
    public Clear() {
        super(false);
    }

    @Override
    public Status execute(String args, connectionHandler connectionHandler) throws IOException {
        var request = new ClearRequest();
        return connectionHandler.sendCommand(request);
    }

    @Override
    public String getDescription() {
        return "удаляет всю коллекцию целиком";
    }

    @Override
    public String getName() {
        return "clear";
    }
}
