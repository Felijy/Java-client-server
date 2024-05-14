package requests;

import commandsHendler.commands.RemoveByComment;
import handler.DBHandler;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.io.Serial;

public class RemoveByCommentRequest extends Request{
    @Serial
    private static final long serialVersionUID = 206;
    private String comment;
    public RemoveByCommentRequest(String comment) {
        super("remove_by_comment");
        this.comment = comment;
    }

    @Override
    public Status execute() {
        if (!DBHandler.checkPassword(getLogin(), getPassword())) {
            return new SQLExceptionStatus();
        }
        RemoveByComment removeByComment = new RemoveByComment();
        return removeByComment.execute(comment, getLogin());
    }
}
