package commandsHendler.commands;

import commandsHendler.Command;
import handler.mapHandler;
import information.ShowInfo;
import statuses.OKResponseStatus;
import statuses.OKStatus;
import statuses.Status;

/**
 * Команда для вывода всех элементов коллекции в строковом представлении
 */
public class Show extends Command {

    public Show() {
        super(false);
    }
    /**
     * Выводит все элементы коллекции в строковом представлении
     */
    @Override
    public Status execute(String args) {
        ShowInfo showInfo = new ShowInfo();
        showInfo.setRes(mapHandler.getCollection());
        return new OKResponseStatus(showInfo);
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public String getName() {
        return "show";
    }
}
