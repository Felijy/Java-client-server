package commandsHendler.commands;

import commandsHendler.Command;
import handler.mapHandler;
import handler.terminalHandler;
import information.MaxVenueInfo;
import statuses.ExceptionStatus;
import statuses.OKResponseStatus;
import statuses.OKStatus;
import statuses.Status;

/**
 * Команда для нахождения элемента с максимальным VenueID в коллекции
 */
public class MaxVenue extends Command {
    public MaxVenue() {
        super(false);
    }

    public Status execute(String args) {
        var collection = mapHandler.getCollection();
        var maxId = collection.values().stream()
                .filter(item -> item.getVenue() != null)
                .mapToLong(item -> item.getVenue().getId())
                .max()
                .orElse(-1);

        if (maxId == -1)
            return new ExceptionStatus();

        var maxVenueInfo = new MaxVenueInfo();
        collection.forEach((key, value) -> {
            if (value.getVenue() != null && value.getVenue().getId() == maxId)
                maxVenueInfo.setTicket(value);
        });

        return new OKResponseStatus(maxVenueInfo);
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
