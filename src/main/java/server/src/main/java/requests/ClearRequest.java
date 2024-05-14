package requests;

import commandsHendler.commands.Clear;
import handler.DBHandler;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.io.Serial;

public class ClearRequest extends Request{
    @Serial
    private static final long serialVersionUID = 201;
    public ClearRequest() {
        super("clear");
    }

    @Override
    public Status execute() {
        if (!DBHandler.checkPassword(getLogin(), getPassword())) {
            return new SQLExceptionStatus();
        }
        Clear clear = new Clear();
        var status = clear.execute(null);
        return status;
    }
}
