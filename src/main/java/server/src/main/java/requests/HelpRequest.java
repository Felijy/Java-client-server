package requests;

import commandsHendler.commands.Help;
import handler.DBHandler;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.io.Serial;

public class HelpRequest extends Request{
    @Serial
    private static final long serialVersionUID = 203;
    public HelpRequest() {
        super("help");
    }

    @Override
    public Status execute() {
        if (!DBHandler.checkPassword(getLogin(), getPassword())) {
            return new SQLExceptionStatus();
        }
        Help help = new Help();
        return help.execute(null);
    }
}
