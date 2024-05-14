package commandsHendler.commands;

import commandsHendler.Command;
import handler.connectionHandler;
import requests.CountPriceRequest;
import statuses.Status;

import java.io.IOException;

/**
 * Команда для подсчёта объектов, значение поля price которых соответствуют переданному в аргумент
 */
public class CountPrice extends Command {
    public CountPrice() {
        super(true);
    }
    @Override
    public Status execute(String args, connectionHandler connectionHandler) throws IOException {
        var request = new CountPriceRequest(args);
        return connectionHandler.sendCommand(request);
    }

    @Override
    public String getDescription() {
        return "вывести количество элементов, значение price которых равно заданному в аргументе";
    }

    @Override
    public String getName() {
        return "count_by_price <price>";
    }
}
