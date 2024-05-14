package statuses;

import java.io.Serial;
import java.io.Serializable;

public class ExceptionStatus extends Status implements Serializable {
    @Serial
    private static final long serialVersionUID = 100;
    public ExceptionStatus() {
        super("Exception");
    }

    @Override
    public String getText() {
        return "!!!Возникла ошибка при выполнении команды";
    }
}
