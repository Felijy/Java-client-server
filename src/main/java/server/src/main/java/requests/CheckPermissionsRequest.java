package requests;

import handler.DBHandler;
import statuses.OKStatus;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.io.Serial;

public class CheckPermissionsRequest extends Request{

    private String key;

    @Serial
    private static final long serialVersionUID = 230;
    public CheckPermissionsRequest(String key) {
        super("check_permissions");
        this.key = key;
    }

    @Override
    public Status execute() {
        if (DBHandler.checkPermission(getLogin(), key)) return new OKStatus();
        else return new SQLExceptionStatus();
    }
}
