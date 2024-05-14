package requests;

import commandsHendler.commands.CountPrice;
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
}
