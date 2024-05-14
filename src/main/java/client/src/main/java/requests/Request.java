package requests;

import commandsHendler.Command;
import exceptions.IncorrectInputException;
import handler.CommandHandler;
import handler.userHandler;
import statuses.Status;

import java.io.Serial;
import java.io.Serializable;

public abstract class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 210;
    private String name;

    private String login;
    private String password;

    public Request(String name) {
        this.name = name;
        this.login = userHandler.getLogin();
        this.password = userHandler.getPassword();
    }

    public String getName() {
        return name;
    }

    public Command getCommand() {
        Command command = null;
        try {
            command = CommandHandler.getCommand(getName());
        } catch (IncorrectInputException e) {
            return null;
        }
        return command;
    }

    public Status execute() {
        return null;
    };

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}
