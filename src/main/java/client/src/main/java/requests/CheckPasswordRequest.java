package requests;

import java.io.Serial;

public class CheckPasswordRequest extends Request {

    private String login;
    private String password;

    @Serial
    private static final long serialVersionUID = 220;
    public CheckPasswordRequest(String login, String password) {
        super("check_password");
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
