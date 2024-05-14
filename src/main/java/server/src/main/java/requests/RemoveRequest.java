package requests;

import commandsHendler.commands.Remove;
import handler.DBHandler;
import statuses.SQLExceptionStatus;
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

    @Override
    public Status execute() {
        if (!DBHandler.checkPassword(getLogin(), getPassword())) {
            return new SQLExceptionStatus();
        }
        Remove remove = new Remove();
        return remove.execute(key, getLogin());
    }
}
