package requests;

import commandsHendler.commands.Remove;
import statuses.Status;

import java.io.Serial;

public class RemoveRequest extends Request{
    @Serial
    private static final long serialVersionUID = 209;
    private String key;
    public RemoveRequest(String key) {
        super("remove");
        this.key = key;
    }
}
