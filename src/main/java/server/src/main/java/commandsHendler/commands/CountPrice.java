package commandsHendler.commands;

import commandsHendler.Command;
import data.Ticket;
import handler.mapHandler;
import handler.terminalHandler;
import information.CountPriceInfo;
import statuses.ExceptionStatus;
import statuses.OKResponseStatus;
import statuses.OKStatus;
import statuses.Status;

/**
 * Команда для подсчёта объектов, значение поля price которых соответствуют переданному в аргумент
 */
public class CountPrice extends Command {
    public CountPrice() {
        super(true);
    }

    public Status execute(String args) {
        try {
            float targetPrice = Float.parseFloat(args);
            int count = (int) mapHandler.getCollection().values().stream()
                    .map(Ticket::getPrice)
                    .filter(price -> price == targetPrice)
                    .count();
            CountPriceInfo countPriceInfo = new CountPriceInfo();
            countPriceInfo.setCount(count);
            return new OKResponseStatus(countPriceInfo);

        } catch (NumberFormatException e) {
            return new ExceptionStatus();
        }
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
