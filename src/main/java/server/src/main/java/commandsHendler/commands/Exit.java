package commandsHendler.commands;

import commandsHendler.Command;
import statuses.OKStatus;
import statuses.Status;

/**
 * Команда для досрочного выхода из программы без сохранения данных
 */
public class Exit extends Command {
    public Exit() {
        super(false);
    }

    @Override
    public Status execute(String args) {
        System.exit(0);
        return new OKStatus();
    }

    @Override
    public String getDescription() {
        return "выход без сохранения";
    }

    @Override
    public String getName() {
        return "exit";
    }
}
