package requests;

import commandsHendler.commands.Help;
import statuses.Status;

import java.io.Serial;

public class HelpRequest extends Request{
    @Serial
    private static final long serialVersionUID = 203;
    public HelpRequest() {
        super("help");
    }
}
