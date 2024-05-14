package commandsHendler.commands;

import commandsHendler.Command;
import handler.CommandHandler;
import information.HelpInfo;
import statuses.OKResponseStatus;
import statuses.OKStatus;
import statuses.Status;

/**
 * Команда для вывода справки
 */
public class Help extends Command {
    public Help() {
        super(false);
    }

    @Override
    public Status execute(String args) {
        var commands = CommandHandler.getMap();
        var keys = commands.keySet();
        HelpInfo helpInfo = new HelpInfo();
        for (String i: keys) {
            helpInfo.addPair(commands.get(i).getName(), commands.get(i).getDescription());
        }
        return new OKResponseStatus(helpInfo);
    }

    @Override
    public String getDescription() {
        return "вывод справку по командам";
    }

    @Override
    public String getName() {
        return "help";
    }
}
