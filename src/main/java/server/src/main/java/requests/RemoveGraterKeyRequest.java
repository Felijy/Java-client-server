package requests;

import commandsHendler.commands.RemoveGraterKey;
import handler.DBHandler;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.io.Serial;

public class RemoveGraterKeyRequest extends Request{
    @Serial
    private static final long serialVersionUID = 207;
    private String key;
    public RemoveGraterKeyRequest(String key) {
        super("remove_grater_key");
        this.key = key;
    }

    @Override
    public Status execute() {
        if (!DBHandler.checkPassword(getLogin(), getPassword())) {
            return new SQLExceptionStatus();
        }
        RemoveGraterKey removeGraterKey = new RemoveGraterKey();
        return removeGraterKey.execute(key, getLogin());
    }
}
