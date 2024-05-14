package commandsHendler.serverCommands;

import commandsHendler.Command;
import statuses.Status;

public class ServerExit extends Command {
    public ServerExit() {
        super(false);
    }

    @Override
    public Status execute(String args) {
        System.exit(0);
        return null;
    }

    @Override
    public String getDescription() {
        return "выход";
    }

    @Override
    public String getName() {
        return "exit";
    }
}
