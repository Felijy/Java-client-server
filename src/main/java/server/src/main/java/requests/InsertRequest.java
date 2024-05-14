package requests;

import commandsHendler.AddValue;
import commandsHendler.Command;
import commandsHendler.commands.Insert;
import data.Ticket;
import exceptions.IncorrectInputException;
import handler.CommandHandler;
import handler.DBHandler;
import statuses.ExceptionStatus;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.io.Serial;
import java.sql.SQLException;

public class InsertRequest extends Request{
    @Serial
    private static final long serialVersionUID = 204;
    private Ticket ticket;
    private String key;

    public InsertRequest(String key, Ticket ticket) {
        super("insert");
        this.ticket = ticket;
        this.key = key;

    }

    public Ticket getTicket() {
        return ticket;
    }

    public Status execute() {
        if (!DBHandler.checkPassword(getLogin(), getPassword())) {
            return new SQLExceptionStatus();
        }
        Insert insert = new Insert();
        Status status = new ExceptionStatus();
        try {
            status = insert.execute(key, ticket, DBHandler.getUserId(getLogin()));
        } catch (SQLException e) {
            return new SQLExceptionStatus();
        }
        return status;
    }

    @Override
    public String toString() {
        return "InsertRequest{" +
                "ticket=" + ticket +
                '}';
    }
}
