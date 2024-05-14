package requests;

import commandsHendler.commands.Info;
import handler.DBHandler;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.io.Serial;

public class InfoRequest extends Request{
    @Serial
    private static final long serialVersionUID = 213;
    public InfoRequest() {
        super("info");
    }

    @Override
    public Status execute() {
        if (!DBHandler.checkPassword(getLogin(), getPassword())) {
            return new SQLExceptionStatus();
        }
        Info info = new Info();
        return info.execute(null);
    }
}
