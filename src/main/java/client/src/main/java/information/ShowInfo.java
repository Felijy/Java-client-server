package information;

import data.Ticket;
import handler.terminalHandler;

import java.io.Serial;
import java.util.HashMap;

public class ShowInfo extends Info{
    @Serial
    private static final long serialVersionUID = 305;
    private HashMap<String, Ticket> res;
    public ShowInfo() {
        super("show");
    }

    public HashMap<String, Ticket> getRes() {
        return res;
    }

    public void setRes(HashMap<String, Ticket> res) {
        this.res = res;
    }

    @Override
    public String printInfo() {
        return res.toString();
    }
}
