package statuses;

import java.io.Serial;
import java.io.Serializable;

public class OKStatus extends Status implements Serializable {
    @Serial
    private static final long serialVersionUID = 102;

    public OKStatus() {
        super("OK");
    }

    @Override
    public String getText() {
        return "Команда выполнена успешно";
    }
}
