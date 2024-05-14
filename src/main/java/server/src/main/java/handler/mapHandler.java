package handler;

import data.Coordinates;
import data.Ticket;
import data.Venue;
import data.VenueType;
import statuses.SQLExceptionStatus;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Класс для работы непосредственно с коллекцией билетов
 */
public class mapHandler {
    /**
     * Поле, хранящее коллекцию со всеми элементами Ticket
     */
    private static HashMap<String, Ticket> collection;

    /**
     * Поле для хранения даты и времени инициализации коллекции
     */
    private static final LocalDateTime initTime;

    /**
     * Поле для хранения даты и времени последнего обращения к коллекции
     */
    private static LocalDateTime accessTime;

    private static final ReadWriteLock lock = new ReentrantReadWriteLock();

    static {
        setCollection(new HashMap<>());
        initTime = LocalDateTime.now();
    }

    /**
     * @return коллекцию
     */
    public static HashMap<String, Ticket> getCollection() {
        lock.readLock().lock();
        try {
            accessTime = LocalDateTime.now();
            return collection;
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Устанавливает новую коллекцию
     * @param collection новая коллекция
     */
    public static void setCollection(HashMap<String, Ticket> collection) {
        lock.writeLock().lock();
        try {
            mapHandler.collection = collection;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Метод для проверки наличия/отсутствия ключа в коллекции
     * @param key ключ, который нужно проверить
     * @return true, если ключ корректен и ещё не использовался в коллекции, false -- в обратом случае
     */
    public static boolean checkKey(String key) {
        return !collection.containsKey(key);
    }

    /**
     * Метод для создания нового элемента в коллекции (создание нового Ticket)
     * @param key ключ, который будет присвоен данному элементу
     * @param obj непосредственно элемент, который должен быть добавлен
     */
    public static boolean makeNewTicket(String key, Ticket obj, int id) {
        lock.writeLock().lock();
        try {
            boolean isOK = false;
            try {
                isOK = DBHandler.createTicket(key, obj, id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (isOK) {
                try {
                    collection.put(key, DBHandler.getTicket(key));
                } catch (SQLException e) {
                    return false;
                }
            }
            return isOK;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static boolean updateTicket(String key, Ticket obj) {
        lock.writeLock().lock();
        try {
            boolean isOK = false;
            try {
                isOK = DBHandler.updateTicket(key, obj);
            } catch (SQLException e) {
                return false;
            }
            if (isOK) {
                collection.remove(key);
                try {
                    collection.put(key, DBHandler.getTicket(key));
                } catch (SQLException e) {
                    return false;
                }
            }
            return isOK;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Метод для удаления элемента из коллекции
     * @param key ключ элемента, который нужно удалить
     */
    public static void deleteElement(String key, String login) throws SQLException {
        lock.writeLock().lock();
        try {
            if (!DBHandler.checkPermission(login, key)) {
                throw new SQLException();
            }
            DBHandler.removeByKey(key);
            collection.remove(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static LocalDateTime getInitTime() {
        return initTime;
    }

    public static LocalDateTime getAccessTime() {
        return accessTime;
    }
}
