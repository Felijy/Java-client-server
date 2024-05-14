package requests;

import commandsHendler.commands.Insert;
import commandsHendler.commands.Update;
import data.Ticket;
import handler.DBHandler;
import handler.mapHandler;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.io.Serial;
import java.sql.SQLException;

public class UpdateRequest extends Request{
    @Serial
    private static final long serialVersionUID = 212;
    private Ticket ticket;
    private String key;

    public UpdateRequest(String key, Ticket ticket) {
        super("update");
        this.ticket = ticket;
        this.key = key;
    }

    public Status execute() {
        if (!DBHandler.checkPassword(getLogin(), getPassword())) {
            return new SQLExceptionStatus();
        }
        if (!DBHandler.checkPermission(getLogin(), key)) {
            return new SQLExceptionStatus();
        }
        Update update = new Update();
        Status status = null;
        try {
            status = update.execute(key, ticket, DBHandler.getUserId(getLogin()));
        } catch (SQLException e) {
            return new SQLExceptionStatus();
        }
        return status;
    }
}
