package commandsHendler.commands;

import commandsHendler.AddValue;
import data.*;
import handler.mapHandler;
import handler.terminalHandler;
import statuses.ExceptionStatus;
import statuses.OKStatus;
import statuses.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Команда для добавления в коллекцию нового элемента
 */
public class Insert extends AddValue {
    public Insert() {
        super(true);
    }

    @Override
    public Status execute(String key, Ticket ticket, int id) {
        if (mapHandler.getCollection().containsKey(key)) return new ExceptionStatus();
        boolean isOK = mapHandler.makeNewTicket(key, ticket, id);
        if (isOK) {
            return new OKStatus();
        } else {
            return new ExceptionStatus();
        }
    }

    @Override
    public Status execute(String args) {
        return null;
    }

    @Override
    public String getDescription() {
        return "Добавить в коллекцию новый элемент, в качестве аргумента указывается ключ нового элемента";
    }

    @Override
    public String getName() {
        return "insert <key> {element}";
    }

}
