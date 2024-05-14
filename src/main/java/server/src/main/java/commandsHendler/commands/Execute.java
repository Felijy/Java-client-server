package commandsHendler.commands;

import commandsHendler.Command;
import handler.Run;
import handler.terminalHandler;
import statuses.OKStatus;
import statuses.Status;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Команда, которая выполняет скрип из файла так, если бы команды вводил пользователь
 */
public class Execute extends Command {
    public Execute() {
        super(true);
    }

    private boolean isOpenFile;

    @Override
    public Status execute(String args) {
        //if (isOpenFile) return false;
        File currentFile = new File(args);
        try {
            terminalHandler.setFile(currentFile);
        } catch (FileNotFoundException e) {
            System.out.print("!!!Файл не найден. ");
            //return false;
        }
        Run run = new Run();
        isOpenFile = true;
        while (terminalHandler.getFromFile()) {
            run.waitForCommand();
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
