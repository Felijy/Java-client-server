package commandsHendler.commands;

import commandsHendler.Command;
import data.Ticket;
import handler.mapHandler;
import statuses.ExceptionStatus;
import statuses.OKStatus;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Команда для удаления всех элементов коллекции, которые больше данного (по id класса Ticket)
 */
public class RemoveGrater extends Command {
    public RemoveGrater() {
        super(true);
    }

    @Override
    public Status execute(String args) {
        return null;
    }

    @Override
    public Status execute(String args, String login) {
        if (args.equals("")) return new ExceptionStatus();

        try {
            Long id = Long.parseLong(args);
            var collection = mapHandler.getCollection();

            Ticket curTicket = collection.values().stream()
                    .filter(ticket -> ticket.getId().equals(id))
                    .findFirst()
                    .orElse(null);

            if (curTicket == null) return new ExceptionStatus();

            List<String> deletedKeys = collection.keySet().stream()
                    .filter(key -> collection.get(key).compareTo(curTicket) > 0)
                    .collect(Collectors.toList());

            for (String key : deletedKeys) {
                try {
                    mapHandler.deleteElement(key, login);
                } catch (SQLException e) {
                    return new SQLExceptionStatus();
                }
            }

            if (!deletedKeys.isEmpty()) {
                return new OKStatus();
            } else {
                return new ExceptionStatus();
            }

        } catch (NumberFormatException e) {
            return new ExceptionStatus();
        }
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
