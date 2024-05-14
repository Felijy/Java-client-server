package requests;

import commandsHendler.commands.MaxVenue;
import statuses.Status;

import java.io.Serial;

public class MaxVenueRequest extends Request{
    @Serial
    private static final long serialVersionUID = 205;
    public MaxVenueRequest() {
        super("max_by_venue");
    }

}
