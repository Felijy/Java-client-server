package handler;

import commandsHendler.Command;
import exceptions.IncorrectInputException;

import java.util.HashMap;

public class commandsServerHandler {
    static private HashMap<String, Command> commands;

    public commandsServerHandler() {}

    static {
        commands = new HashMap<>();
    }

    public static void addCommand(String name, Command command) {
        commands.put(name, command);
    }

    public static Command getCommand(String arg) throws IncorrectInputException {
        Command command = commands.get(arg);
        if (command == null) throw new IncorrectInputException();
        return command;
    }
}
