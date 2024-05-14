package handler;

import commandsHendler.commands.*;
import commandsHendler.serverCommands.ServerExit;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Класс для старта и перехода в интерактивный режим работы с пользователем
 */
public class Run {
    /**
     * enum, в котором хранятся exitCode, которые возвращаются в зависимости от успешности/не успешности завершения команды
     */
    private enum ExitCode {
        OK,
        ERROR,
        STOP,
        REPEAT;
    }

    public Run() {}


    public void startServer() {
        DBHandler db = new DBHandler();
        try {
            DBHandler.refreshMap();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CommandHandler.addCommand("insert", new Insert());
        CommandHandler.addCommand("show", new Show());
        CommandHandler.addCommand("update", new Update());
        CommandHandler.addCommand("remove_key", new Remove());
        CommandHandler.addCommand("clear", new Clear());
        CommandHandler.addCommand("exit", new Exit());
        CommandHandler.addCommand("help", new Help());
        CommandHandler.addCommand("execute_script", new Execute());
        CommandHandler.addCommand("history", new History());
        CommandHandler.addCommand("count_by_price", new CountPrice());
        CommandHandler.addCommand("info", new Info());
        CommandHandler.addCommand("remove_grater_key", new RemoveGraterKey());
        CommandHandler.addCommand("remove_any_by_comment", new RemoveByComment());
        CommandHandler.addCommand("remove_grater", new RemoveGrater());
        CommandHandler.addCommand("max_by_venue", new MaxVenue());

        commandsServerHandler.addCommand("exit", new ServerExit());
        waitForCommand();
    }


    public void waitForCommand() {
        ExitCode currentCode;
        connectionHandler connectionHandler = null;
        try {
            connectionHandler = new connectionHandler();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        do {
            connectionHandler.startServer();
            currentCode = ExitCode.REPEAT;
        } while (currentCode != ExitCode.STOP);
    }

}
