package commandsHendler.commands;

import commandsHendler.AddValue;
import data.*;
import handler.connectionHandler;
import handler.terminalHandler;
import requests.CheckPermissionsRequest;
import requests.UpdateRequest;
import statuses.ExceptionStatus;
import statuses.OKStatus;
import statuses.SQLExceptionStatus;
import statuses.Status;

import java.io.IOException;
import java.util.Objects;

/**
 * Команда для изменения в коллекции элемента по его ключу
 */
public class Update extends AddValue {
    public Update() {
        super(true);
    }

    @Override
    public Status execute(String args, connectionHandler connectionHandler) throws IOException {
        if (connectionHandler.sendCommand(new CheckPermissionsRequest(args)).getClass() != OKStatus.class) {
            return new SQLExceptionStatus();
        }
        System.out.print("Начато изменение элемента с ключом " + args + "\n");
        Ticket ticket = new Ticket();
        Venue venue = new Venue();
        Coordinates coordinates = new Coordinates();
        ticket.setName(startAdding("название билета (не пустое)", false, null, null, false));
        ticket.setPrice(Float.parseFloat(startAdding("цену (больше нуля)", false, 0, null, false)));
        try {
            ticket.setDiscount(Integer.parseInt(startAdding("скидку (больше нуля, меньше 100)", false, 0, 100, false)));
        } catch (NumberFormatException e){
            return new ExceptionStatus();
        }
        ticket.setComment(startAdding("комментарий", true, null, null, true));
        String currentType;
        do {
            currentType = startAdding("тип билета (USUAL, BUDGETARY, CHEAP)", true, null, null, false);
            if (currentType == null) break;
            if ((!checkTicketTypeEnum(currentType))) terminalHandler.printlnA("!!!Ошибка. Попробуйте еще раз.");
        } while (!checkTicketTypeEnum(currentType));
        if (currentType != null) ticket.setType(TicketType.valueOf(currentType));
        else ticket.setType(null);
        coordinates.setX(Float.parseFloat(startAdding("координату по оси X, меньше 766", false, null, 766, false)));
        coordinates.setY(Float.parseFloat(startAdding("координату по оси Y", false, null, null, false)));
        ticket.setCoordinates(coordinates);
        terminalHandler.print("Добавить место? 'yes' чтобы добавить, любой другой символ, чтобы нет: ");
        String answer = terminalHandler.readLine();
        if (Objects.equals(answer, "yes")) {
            venue.setName(startAdding("название места (не пустое)", false, null, null, false));
            try {
                venue.setCapacity(Long.parseLong(startAdding("вместимость, больше нуля", false, 0, null, false)));
            } catch (NumberFormatException e) {
                return new ExceptionStatus();
            }
            do {
                currentType = startAdding("тип места (BAR, LOFT, OPEN_AREA, THEATRE, STADIUM)", false, null, null, false);
                if (!checkVenueTypeEnum(currentType)) terminalHandler.printlnA("!!!Ошибка. Попробуйте еще раз.");
            } while (!checkVenueTypeEnum(currentType));
            venue.setType(VenueType.valueOf(currentType));
            ticket.setVenue(venue);
        } else ticket.setVenue(null);

        var request = new UpdateRequest(args, ticket);
        return connectionHandler.sendCommand(request);
    }


    /**
     * Метод для старта добавления нового элемента. Получив ввод от пользователя, вызывает метод ValidateNew() в абстрактном супер-классе
     */
    private String startAdding (String name, boolean canBeNull, Integer moreThen,
                                Integer lessThen, boolean canBeEmptyString) {
        boolean isEnd;
        String currentInput;
        do {
            System.out.print("Введите " + name + ": ");
            currentInput = terminalHandler.readLine();
            isEnd = validateNew(currentInput, canBeNull, moreThen, lessThen, canBeEmptyString);
            if (!isEnd) System.out.print("!!!Ошибка ввода. Попробуйте еще раз\n");
        } while (!isEnd);
        return currentInput;
    }

    /**
     * Метод для проверки, входит ли значение arg в Enum TicketType
     * @param arg значение, которое нужно проверить
     * @return true, если значение входит в enum, false -- в обратном случае
     */
    private boolean checkTicketTypeEnum(String arg) {
        try {
            TicketType.valueOf(arg);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    /**
     * Метод для проверки, входит ли значение arg в Enum VenueType
     * @param arg значение, которое нужно проверить
     * @return true, если значение входит в enum, false -- в обратном случае
     */
    private boolean checkVenueTypeEnum(String arg) {
        try {
            VenueType.valueOf(arg);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "Изменяет элемент в коллекции, в качестве аргумента указывается ключ изменяемого элемента";
    }

    @Override
    public String getName() {
        return "update <key> {element}";
    }
}
