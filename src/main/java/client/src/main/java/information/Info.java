package information;

import java.io.Serial;
import java.io.Serializable;

public abstract class Info implements Serializable {
    @Serial
    private static final long serialVersionUID = 302;
    private String name;

    public Info(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String printInfo();


}