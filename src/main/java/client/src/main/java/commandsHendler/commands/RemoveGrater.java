package commandsHendler.commands;

import commandsHendler.Command;
import handler.connectionHandler;
import requests.RemoveGraterRequest;
import statuses.Status;

import java.io.IOException;

/**
 * Команда для удаления всех элементов коллекции, которые больше данного (по id класса Ticket)
 */
public class RemoveGrater extends Command {
    public RemoveGrater() {
        super(true);
    }

    @Override
    public Status execute(String args, connectionHandler connectionHandler) throws IOException {
        var request = new RemoveGraterRequest(args);
        return connectionHandler.sendCommand(request);
    }

    @Override
    public String getDescription() {
        return "удаляет все элементы, которые больше заданного (текущий элемент определяется по id)";
    }

    @Override
    public String getName() {
        return "remove_grater <id>";
    }
}
