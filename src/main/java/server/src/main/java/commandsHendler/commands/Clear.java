package commandsHendler.commands;

import commandsHendler.Command;
import handler.DBHandler;
import handler.mapHandler;
import statuses.ExceptionStatus;
import statuses.OKStatus;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Команда для очистки коллекции
 */
public class Clear extends Command {
    public Clear() {
        super(false);
    }

    @Override
    public Status execute(String args) {
        mapHandler.setCollection(new HashMap<>());
        try {
            DBHandler.executeQuery("TRUNCATE TABLE venue CASCADE;");
            DBHandler.executeQuery("TRUNCATE TABLE tickets CASCADE;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new OKStatus();
    }

    @Override
    public String getDescription() {
        return "удаляет всю коллекцию целиком";
    }

    @Override
    public String getName() {
        return "clear";
    }
}
