package requests;

import commandsHendler.commands.RemoveGrater;
import handler.DBHandler;
import statuses.SQLExceptionStatus;
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

    @Override
    public Status execute() {
        if (!DBHandler.checkPassword(getLogin(), getPassword())) {
            return new SQLExceptionStatus();
        }
        RemoveGrater removeGrater = new RemoveGrater();
        return removeGrater.execute(id, getLogin());
    }
}
