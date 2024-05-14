package commandsHendler.commands;

import commandsHendler.Command;
import handler.connectionHandler;
import requests.MaxVenueRequest;
import statuses.Status;

import java.io.IOException;

/**
 * Команда для нахождения элемента с максимальным VenueID в коллекции
 */
public class MaxVenue extends Command {
    public MaxVenue() {
        super(false);
    }

    @Override
    public Status execute(String args, connectionHandler connectionHandler) throws IOException {
        var request = new MaxVenueRequest();
        return connectionHandler.sendCommand(request);
    }

    @Override
    public String getDescription() {
        return "выводит любой элемент коллекции с максимальным venue (по id)";
    }

    @Override
    public String getName() {
        return "max_by_venue";
    }
}
