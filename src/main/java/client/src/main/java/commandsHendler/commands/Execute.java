package commandsHendler.commands;

import commandsHendler.Command;
import exceptions.IncorrectInputException;
import handler.CommandHandler;
import handler.Run;
import handler.connectionHandler;
import handler.terminalHandler;
import statuses.ExceptionStatus;
import statuses.OKStatus;
import statuses.Status;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Команда, которая выполняет скрип из файла так, если бы команды вводил пользователь
 */
public class Execute extends Command {
    public Execute() {
        super(true);
    }

    private boolean isOpenFile;

    @Override
    public Status execute(String args, connectionHandler connectionHandler) throws IOException {
        if (isOpenFile) return new ExceptionStatus();
        File currentFile = new File(args);
        try {
            terminalHandler.setFile(currentFile);
        } catch (FileNotFoundException e) {
            System.out.print("!!!Файл не найден. ");
            return new ExceptionStatus();
        }
        Run run = new Run();
        isOpenFile = true;
        Scanner scanner = new Scanner(currentFile);
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var command = line.split(" ")[0];
            var agrs = line.split(" ")[1];
            Status status = null;
            if (agrs == null) {
                try {
                    status = CommandHandler.getCommand(command).execute(null, connectionHandler);
                } catch (IncorrectInputException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    status = CommandHandler.getCommand(command).execute(args, connectionHandler);
                } catch (IncorrectInputException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        isOpenFile = false;
        return new OKStatus();
    }

    @Override
    public String getDescription() {
        return "выполняет скрипт из файла, в качестве аргумента указывается имя файла со скриптом";
    }

    @Override
    public String getName() {
        return "execute_script <file_name>";
    }
}
