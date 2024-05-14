package requests;

import commandsHendler.commands.RemoveByComment;
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

}
