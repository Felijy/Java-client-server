package requests;

import java.io.Serial;
import java.io.Serializable;

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
}
