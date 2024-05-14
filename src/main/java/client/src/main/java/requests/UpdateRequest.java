package requests;

import commandsHendler.commands.Insert;
import commandsHendler.commands.Update;
import data.Ticket;
import statuses.Status;

import java.io.Serial;

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
}
