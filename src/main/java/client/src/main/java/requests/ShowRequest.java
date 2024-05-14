package requests;

import commandsHendler.commands.Show;
import statuses.Status;

import java.io.Serial;

public class ShowRequest extends Request{
    @Serial
    private static final long serialVersionUID = 211;
    public ShowRequest() {
        super("show");
    }
}
