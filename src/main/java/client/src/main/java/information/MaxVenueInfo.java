package information;

import GUI.Lang;
import data.Ticket;
import handler.terminalHandler;

import java.awt.*;
import java.io.Serial;

public class MaxVenueInfo extends Info{
    @Serial
    private static final long serialVersionUID = 304;
    private Ticket ticket;
    public MaxVenueInfo() {
        super("info");
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String printInfo() {
        String res = Lang.Info.getMaxVenue() + "\n";
        res += ticket.toString();
        return res;
    }
}
