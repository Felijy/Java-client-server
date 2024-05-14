package handler;

import org.apache.commons.lang3.tuple.Pair;
import requests.CheckPasswordRequest;
import requests.ExistUserRequest;
import requests.NewUserRequest;
import statuses.OKStatus;
import statuses.Status;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class userHandler {

    private static String login = null;
    private static String password = null;
    private static connectionHandler connectionHandler = null;


    public static void askUser (connectionHandler connectionHandler) throws IOException {
        terminalHandler.print("Введите логин: ");
        login = terminalHandler.readLine();
        Status status = connectionHandler.sendCommand(new ExistUserRequest(login));
        if (status.getClass() == OKStatus.class) {
            terminalHandler.print("Пользователь найден! Введите пароль: ");
            boolean correct = askPassword(connectionHandler);
            if (correct) {
                terminalHandler.println("Пароль успешно принят!");
                terminalHandler.println("Вы работаете как пользователь: " + login);
            } else {
                password = null;
                terminalHandler.println("Пароль неверен!");
                askUser(connectionHandler);
            }
        } else {
            terminalHandler.println("Такого пользователя не существует. Начато добавление пользователя с логином " + login);
            terminalHandler.print("Придумайте пароль: ");
            boolean correct = createPassword(connectionHandler);
            if (correct) {
                terminalHandler.println("Пользователь успешно создан!");
                terminalHandler.println("Вы работаете как пользователь: " + login);
            }
        }
    }

    public static boolean askPassword(connectionHandler connectionHandler) throws IOException {
        password = terminalHandler.readLine();
        password = hashPassword(password);
        Status status = connectionHandler.sendCommand(new CheckPasswordRequest(login, password));
        if (status.getClass() == OKStatus.class) {
            return true;
        }
        return false;
    }

    public static boolean createPassword (connectionHandler connectionHandler) throws IOException {
        password = terminalHandler.readLine();
        password = hashPassword(password);
        Status status = connectionHandler.sendCommand(new NewUserRequest(login, password));
        if (status.getClass() == OKStatus.class) {
            return true;
        }
        return false;
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getLogin() {
        return login;
    }

    public static String getPassword() {
        return password;
    }

    public static void setLogin(String login) {
        userHandler.login = login;
    }

    public static void setPassword(String password) {
        userHandler.password = password;
    }

    public static handler.connectionHandler getConnectionHandler() {
        return connectionHandler;
    }

    public static void setConnectionHandler(handler.connectionHandler connectionHandler) {
        userHandler.connectionHandler = connectionHandler;
    }
}