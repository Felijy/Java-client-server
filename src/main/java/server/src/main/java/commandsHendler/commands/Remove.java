package commandsHendler.commands;

import commandsHendler.Command;
import handler.mapHandler;
import statuses.ExceptionStatus;
import statuses.OKStatus;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.sql.SQLException;
import java.util.Objects;

/**
 * Команда для удаления элемента по ключу
 */
public class Remove extends Command {
    public Remove() {
        super(true);
    }

    @Override
    public Status execute(String args) {
        return null;
    }

    @Override
    public Status execute(String args, String login) {
        String key = args;
        if (mapHandler.checkKey(key)) return new ExceptionStatus();

        try {
            mapHandler.getCollection().entrySet().stream()
                    .filter(entry -> Objects.equals(entry.getKey(), key))
                    .findFirst()
                    .ifPresent(entry -> {
                        try {
                            mapHandler.deleteElement(entry.getKey(), login);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (RuntimeException e) {
            return new SQLExceptionStatus();
        }
        return new OKStatus();
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
