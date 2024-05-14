package requests;

import commandsHendler.commands.CountPrice;
import handler.DBHandler;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.io.Serial;

public class CountPriceRequest extends Request{
    @Serial
    private static final long serialVersionUID = 202;
    private String price;

    public CountPriceRequest(String price) {
        super("count_by_price");
        this.price = price;
    }

    @Override
    public Status execute() {
        if (!DBHandler.checkPassword(getLogin(), getPassword())) {
            return new SQLExceptionStatus();
        }
        CountPrice countPrice = new CountPrice();
        return countPrice.execute(price);
    }
}
