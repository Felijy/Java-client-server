package requests;

import commandsHendler.commands.Info;
import statuses.Status;

import java.io.Serial;

public class InfoRequest extends Request{
    @Serial
    private static final long serialVersionUID = 213;
    public InfoRequest() {
        super("info");
    }
}
