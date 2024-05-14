package requests;

import java.io.Serial;

public class ExistUserRequest extends Request {

    private String login;

    @Serial
    private static final long serialVersionUID = 221;
    public ExistUserRequest(String login) {
        super("exist_user");
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
