package information;

import GUI.Lang;
import handler.terminalHandler;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class InfoInfo extends Info implements Serializable {
    @Serial
    private static final long serialVersionUID = 303;
    public InfoInfo() {
        super("info");
    }

    private LocalDateTime init;
    private LocalDateTime lastAccess;
    private Integer size;
    private String typeData;

    public LocalDateTime getInit() {
        return init;
    }

    public void setInit(LocalDateTime init) {
        this.init = init;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getTypeData() {
        return typeData;
    }

    public void setTypeData(String typeData) {
        this.typeData = typeData;
    }

    @Override
    public String printInfo() {
        String res = "";
        res += Lang.Info.getInfoRow1() + "\n";
        res += Lang.Info.getInfoRow2() + init + "\n";
        res += Lang.Info.getInfoRow3() + lastAccess + "\n";
        res += Lang.Info.getInfoRow4() + size + "\n";
        res += Lang.Info.getInfoRow5() + typeData;
        return res;
    }
}
