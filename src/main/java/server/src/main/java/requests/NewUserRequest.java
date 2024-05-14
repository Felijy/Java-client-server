package requests;

import handler.DBHandler;
import statuses.ExceptionStatus;
import statuses.OKStatus;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.io.Serial;
import java.sql.SQLException;

public class NewUserRequest extends Request {

    private String login;
    private String password;

    @Serial
    private static final long serialVersionUID = 222;

    public NewUserRequest(String login, String password) {
        super("new_user");
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Status execute() {
        try {
            if (DBHandler.createUser(login, password)) {
                return new OKStatus();
            } else {
                return new ExceptionStatus();
            }
        } catch (SQLException e) {
            return new SQLExceptionStatus();
        }
    }
}
