package information;

import data.Ticket;
import handler.terminalHandler;

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
    public void printInfo() {
        terminalHandler.printlnA("Билет с наибольшим venue:");
        terminalHandler.printlnA(ticket.toString());
    }
}
