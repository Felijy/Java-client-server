package data;

import java.io.Serializable;

/**
 * enum, хранящий возможные типы Ticket
 */
public enum TicketType implements Serializable {
    USUAL,
    BUDGETARY,
    CHEAP;
}
