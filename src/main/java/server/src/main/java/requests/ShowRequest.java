package requests;

import commandsHendler.commands.Show;
import handler.DBHandler;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.io.Serial;
import java.sql.SQLException;

public class ShowRequest extends Request{
    @Serial
    private static final long serialVersionUID = 211;
    public ShowRequest() {
        super("show");
    }

    @Override
    public Status execute() {
        if (!DBHandler.checkPassword(getLogin(), getPassword())) {
            return new SQLExceptionStatus();
        }
        try {
            DBHandler.refreshMap();
        } catch (SQLException e) {
            return new SQLExceptionStatus();
        }
        Show show = new Show();
        return show.execute(null);
    }
}
