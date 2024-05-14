package requests;

import handler.DBHandler;
import statuses.OKStatus;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.io.Serial;

public class CheckPasswordRequest extends Request {
    private String login;
    private String password;

    @Serial
    private static final long serialVersionUID = 220;
    public CheckPasswordRequest() {
        super("check_password");
    }

    @Override
    public Status execute() {
        if (DBHandler.checkPassword(login, password)) {
            return new OKStatus();
        } else {
            return new SQLExceptionStatus();
        }
    }
}
