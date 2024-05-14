package requests;

import commandsHendler.AddValue;
import commandsHendler.Command;
import commandsHendler.commands.Insert;
import data.Ticket;
import exceptions.IncorrectInputException;
import handler.CommandHandler;
import statuses.Status;

import java.io.Serial;

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


    @Override
    public String toString() {
        return "InsertRequest{" +
                "ticket=" + ticket +
                '}';
    }
}
