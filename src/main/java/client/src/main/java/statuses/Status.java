package statuses;

import java.io.Serial;
import java.io.Serializable;

public abstract class Status implements Serializable {
    @Serial
    private static final long serialVersionUID = 103;
    private String name;

    Status(String name) {
        this.name = name;
    }

    public abstract String getText();

    public String getName() {
        return name;
    }
}
