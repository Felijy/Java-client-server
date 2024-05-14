package statuses;

import information.Info;

import java.io.Serial;
import java.io.Serializable;

public class OKResponseStatus extends Status implements Serializable {
    @Serial
    private static final long serialVersionUID = 101;
    private Info info;

    public OKResponseStatus(Info info) {
        super("OKResponse");
        this.info = info;
    }

    public Info getInfo() {
        return info;
    }

    @Override
    public String getText() {
        return "Команда выполнена успешно";
    }
}
