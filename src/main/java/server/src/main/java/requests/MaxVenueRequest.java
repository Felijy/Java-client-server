package requests;

import commandsHendler.commands.MaxVenue;
import handler.DBHandler;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.io.Serial;

public class MaxVenueRequest extends Request{
    @Serial
    private static final long serialVersionUID = 205;
    public MaxVenueRequest() {
        super("max_by_venue");
    }

    @Override
    public Status execute() {
        if (!DBHandler.checkPassword(getLogin(), getPassword())) {
            return new SQLExceptionStatus();
        }
        MaxVenue maxVenue = new MaxVenue();
        return maxVenue.execute(null);
    }
}
