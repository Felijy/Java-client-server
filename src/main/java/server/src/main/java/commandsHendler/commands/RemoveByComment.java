package commandsHendler.commands;

import commandsHendler.Command;
import handler.mapHandler;
import statuses.ExceptionStatus;
import statuses.OKStatus;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

/**
 * Команда для удаления элемента по совпадению в нем поля comment с аргументом
 */
public class RemoveByComment extends Command {
    public RemoveByComment() {
        super(true);
    }

    @Override
    public Status execute(String args) {
        return null;
    }

    @Override
    public Status execute(String args, String login) {
        var collection = mapHandler.getCollection();
        var keyToRemove = collection.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue().getComment(), args))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        if (keyToRemove != null) {
            try {
                mapHandler.deleteElement(keyToRemove, login);
            } catch (SQLException e) {
                return new SQLExceptionStatus();
            }
            return new OKStatus();
        } else {
            return new ExceptionStatus();
        }
    }


    @Override
    public String getDescription() {
        return "удаляет первый объект, значение поля comment которого совпадает с аргументом";
    }

    @Override
    public String getName() {
        return "remove_any_by_comment <comment>";
    }
}
