package commandsHendler;

import data.Ticket;
import statuses.Status;

import java.util.Objects;

/**
 * Абстрактный класс, являющиеся родительским для всех классов, добавляющих элементы в коллекцию
 */
public abstract class AddValue extends Command implements Executable {
    private final boolean anArgument;

    public AddValue(boolean arg) {
        super(arg);
        this.anArgument = arg;
    }

    public abstract Status execute(String key, Ticket ticket, int id);

    /**
     * Абстрактный метод для получения описания команды
     * @return описание команды
     */
    public abstract String getDescription();

    /**
     * Абстрактный метод для получения названия команды
     * @return название команды
     */
    public abstract String getName();

    /**
     * Метод, возвращающий проверку функции на наличие/отсутствие аргумента
     * @return true, если наличие/отсутствие аргумента корректно, false в обратном случае
     */
    public boolean hasAnArgument(int length) {
        if (anArgument && (length > 1)) return true;
        if (!anArgument && (length == 1)) return true;
        return false;
    }
}

