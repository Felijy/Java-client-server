package commandsHendler.commands;

import commandsHendler.AddValue;
import commandsHendler.Command;
import data.*;
import handler.mapHandler;
import handler.terminalHandler;
import statuses.ExceptionStatus;
import statuses.OKStatus;
import statuses.Status;

import java.time.LocalDate;

/**
 * Команда для изменения в коллекции элемента по его ключу
 */
public class Update extends AddValue {
    public Update() {
        super(true);
    }

    @Override
    public Status execute(String key, Ticket ticket, int id) {
        if (!mapHandler.getCollection().containsKey(key)) return new ExceptionStatus();
        boolean isOK = mapHandler.updateTicket(key, ticket);
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
        return "Обновляет в коллекции элемент, в качестве аргумента указывается ключ нового элемента";
    }

    @Override
    public String getName() {
        return "update <key> {element}";
    }

}

