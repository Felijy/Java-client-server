package requests;

import handler.DBHandler;
import statuses.ExceptionStatus;
import statuses.OKStatus;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.io.Serial;
import java.sql.SQLException;

public class ExistUserRequest extends Request {
    @Serial
    private static final long serialVersionUID = 221;

    private String login;

    public ExistUserRequest() {
        super("exist_user");
    }

    @Override
    public Status execute() {
        try {
            if (DBHandler.isUserExists(login)) {
                return new OKStatus();
            }
        } catch (SQLException e) {
            return new SQLExceptionStatus();
        }
        return new ExceptionStatus();
    }
}
