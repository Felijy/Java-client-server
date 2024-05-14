package requests;

import commandsHendler.commands.Clear;
import statuses.Status;

import java.io.Serial;

public class ClearRequest extends Request{
    @Serial
    private static final long serialVersionUID = 201;
    public ClearRequest() {
        super("clear");
    }

}
