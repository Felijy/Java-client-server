package information;

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
    public void printInfo() {
        terminalHandler.printlnA("Основная информация о коллекции:");
        terminalHandler.printlnA("Инициализация: " + init);
        terminalHandler.printlnA("Последний доступ: " + lastAccess);
        terminalHandler.printlnA("Размер: " + size);
        terminalHandler.printlnA("Тип данных: " + typeData);

    }
}
