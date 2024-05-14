package commandsHendler.commands;

import commandsHendler.Command;
import handler.connectionHandler;
import requests.RemoveByCommentRequest;
import statuses.Status;

import java.io.IOException;

/**
 * Команда для удаления элемента по совпадению в нем поля comment с аргументом
 */
public class RemoveByComment extends Command {
    public RemoveByComment() {
        super(true);
    }

    @Override
    public Status execute(String args, connectionHandler connectionHandler) throws IOException {
        var request = new RemoveByCommentRequest(args);
        return connectionHandler.sendCommand(request);
    }

    @Override
    public String getDescription() {
        return "удаляет первый объект, значение поля comment которого совпадает с аргументом";
    }

    @Override
    public String getName() {
        return "remove_any_by_comment <comment>";
    }
}
