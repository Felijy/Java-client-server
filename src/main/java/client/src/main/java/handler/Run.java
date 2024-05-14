package handler;

import commandsHendler.Command;
import commandsHendler.commands.*;
import exceptions.IncorrectInputException;
import statuses.OKResponseStatus;
import statuses.Status;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.time.LocalDateTime;

/**
 * Класс для старта и перехода в интерактивный режим работы с пользователем
 */
public class Run {

    private connectionHandler connectionHandler;

    private String login;
    private String password;

    /**
     * enum, в котором хранятся exitCode, которые возвращаются в зависимости от успешности/не успешности завершения команды
     */
    private enum ExitCode {
        OK,
        ERROR,
        STOP,
        REPEAT;
    }

    /**
     * Пустой конструктор (т.к. класс не имеет полей)
     */
    public Run() {
        login = null;
        password = null;
    }

    {
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
    }


    public void startClient() {
        try {
            connectionHandler = new connectionHandler();
            terminalHandler.println("Подключение успешно!");
            chooseCommand();
        } catch (IOException e) {
            //ErrorDialog errorDialog = new ErrorDialog();

            retryConnection();
        }
    }

    /**
     * Переход в интерактивный режим, в котором пользователь может вводить команды
     */
    public void chooseCommand() throws IOException {
        ExitCode currentCode;
        userHandler.askUser(connectionHandler);
        do {
            String inputCommand = terminalHandler.readLine();
            if (inputCommand == null) currentCode = ExitCode.REPEAT;
            else {
                if (inputCommand != "") {
                    String[] commandParts = inputCommand.split(" ", 2);
                    currentCode = executeCommand(commandParts);
                } else {
                    currentCode = ExitCode.REPEAT;
                }
            }
        } while (currentCode != ExitCode.STOP);
    }

    /**executeCommand
     * Метод для выполнения команды, введенной пользователей
     * @param userCommand строка -- команда, которую ввел пользователь
     * @return exitCode в зависимости от успешности/не успешности выполнения команды
     */
    public ExitCode executeCommand(String[] userCommand) throws IOException {
        try {
            Command currentCommand = CommandHandler.getCommand(userCommand[0]);
            if (!currentCommand.hasAnArgument(userCommand.length)) {
                terminalHandler.printlnA("!!!Ошибка аргумента. Повторите ввод.");
                return ExitCode.ERROR;
            }
            Status res;
            if (userCommand.length == 1) {
                res = currentCommand.execute(null, connectionHandler);
            } else {
                res = currentCommand.execute(userCommand[1], connectionHandler);
            }
            terminalHandler.println(res.getText());
            if (res.getName().equals("OKResponse")) {
                information.Info info = ((OKResponseStatus) res).getInfo();
                info.printInfo();
            }
        } catch (IncorrectInputException e) {
            terminalHandler.printlnA("!!!Команда не найдена. Повторите ввод.");
            return ExitCode.ERROR;
        }
        return ExitCode.OK;
    }

    public void retryConnection() {
        var time = LocalDateTime.now().getSecond();
        terminalHandler.printlnA("При соединении с сервером возникли проблемы. Повторная попытка подключения через 5 секунд... Для отмены введите любой символ");
        boolean isTime = false;
        while (!isTime) {
            try {
                if (System.in.available() > 1) {
                    terminalHandler.println("Выход...");
                    System.exit(0);
                }
            } catch (IOException ignore) {}
            if (LocalDateTime.now().minusSeconds(5).getSecond() >= time) {
                isTime = true;
            }
        }
        startClient();
    }
}
