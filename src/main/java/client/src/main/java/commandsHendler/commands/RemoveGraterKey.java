package commandsHendler.commands;

import commandsHendler.Command;
import handler.connectionHandler;
import requests.RemoveGraterKeyRequest;
import statuses.Status;

import java.io.IOException;

/**
 * Команда для удаления всех элементов коллекции, которые больше данного по ключу
 */
public class RemoveGraterKey extends Command {
    public RemoveGraterKey() {
        super(true);
    }

    @Override
    public Status execute(String args, connectionHandler connectionHandler) throws IOException {
        var request = new RemoveGraterKeyRequest(args);
        return connectionHandler.sendCommand(request);
    }

    @Override
    public String getDescription() {
        return "удаляет элементы, ключ которых больше заданного";
    }

    @Override
    public String getName() {
        return "remove_grater_key <key>";
    }
}
