package commandsHendler.commands;

import commandsHendler.Command;
import handler.connectionHandler;
import requests.RemoveRequest;
import statuses.Status;

import java.io.IOException;

/**
 * Команда для удаления элемента по ключу
 */
public class Remove extends Command {
    public Remove() {
        super(true);
    }

    @Override
    public Status execute(String args, connectionHandler connectionHandler) throws IOException {
        var request = new RemoveRequest(args);
        return connectionHandler.sendCommand(request);
    }

    @Override
    public String getDescription() {
        return "удаляет элемент по заданному ключу";
    }

    @Override
    public String getName() {
        return "remove_key";
    }
}
