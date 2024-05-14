package requests;

import commandsHendler.commands.RemoveGrater;
import statuses.Status;

import java.io.Serial;

public class RemoveGraterRequest extends Request{
    @Serial
    private static final long serialVersionUID = 208;
    private String id;
    public RemoveGraterRequest(String id) {
        super("remove_grater");
        this.id = id;
    }
}
