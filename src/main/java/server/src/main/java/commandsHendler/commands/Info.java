package commandsHendler.commands;

import commandsHendler.Command;
import handler.mapHandler;
import handler.terminalHandler;
import information.InfoInfo;
import statuses.OKResponseStatus;
import statuses.OKStatus;
import statuses.Status;

/**
 * Команда для вывода основной информации о коллекции
 */
public class Info extends Command {
    public Info() {
        super(false);
    }

    @Override
    public Status execute(String args) {
        InfoInfo response = new InfoInfo();
        response.setInit(mapHandler.getInitTime());
        response.setLastAccess(mapHandler.getAccessTime());
        response.setSize(mapHandler.getCollection().size());
        response.setTypeData(mapHandler.getCollection().getClass().getName());
        return new OKResponseStatus(response);
    }

    @Override
    public String getDescription() {
        return "выводит основную информацию о коллекции";
    }

    @Override
    public String getName() {
        return "info";
    }
}
