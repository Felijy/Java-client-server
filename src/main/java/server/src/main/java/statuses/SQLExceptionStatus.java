package statuses;

import java.io.Serial;
import java.io.Serializable;

public class SQLExceptionStatus extends Status implements Serializable {
    @Serial
    private static final long serialVersionUID = 105;

    public SQLExceptionStatus() {
        super("SQLException");
    }

    @Override
    public String getText() {
        return "!!!Недостаточно прав для выполнения этой команды";
    }
}
