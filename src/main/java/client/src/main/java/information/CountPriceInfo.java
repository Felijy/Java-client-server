package information;

import GUI.Lang;
import handler.terminalHandler;

import java.io.Serial;

public class CountPriceInfo extends Info{
    @Serial
    private static final long serialVersionUID = 300;
    private Integer count;

    public CountPriceInfo() {
        super("count_by_price");
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    @Override
    public String printInfo() {
        return Lang.Info.getCount() + count;
    }
}
