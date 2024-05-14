package requests;

import java.io.Serial;

public class CheckPermissionsRequest extends Request{

    private String key;

    @Serial
    private static final long serialVersionUID = 230;
    public CheckPermissionsRequest(String key) {
        super("check_permissions");
        this.key = key;
    }
}
