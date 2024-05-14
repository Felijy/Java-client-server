package commandsHendler.commands;

import commandsHendler.Command;
import handler.mapHandler;
import statuses.ExceptionStatus;
import statuses.OKStatus;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.sql.SQLException;
import java.util.HashSet;

/**
 * Команда для удаления всех элементов коллекции, которые больше данного по ключу
 */
public class RemoveGraterKey extends Command {
    public RemoveGraterKey() {
        super(true);
    }

    @Override
    public Status execute(String args) {
        return null;
    }

    @Override
    public Status execute(String args, String login) {
        var keys = mapHandler.getCollection().keySet();
        var collection = mapHandler.getCollection();
        if (!keys.contains(args)) return new ExceptionStatus();

        boolean isDelete = false;
        for (String i : new HashSet<>(keys)) {
            if (isDelete) {
                try {
                    mapHandler.deleteElement(i, login);
                } catch (SQLException e) {
                    return new SQLExceptionStatus();
                }
            }
            if (i.equals(args)) isDelete = true;
        }

        return isDelete ? new OKStatus() : new ExceptionStatus();
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
