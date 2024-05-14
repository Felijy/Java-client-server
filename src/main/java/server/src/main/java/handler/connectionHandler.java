package handler;

import commandsHendler.Command;
import exceptions.IncorrectInputException;
import org.apache.commons.lang3.SerializationUtils;
import requests.Request;
import statuses.Status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.time.LocalTime;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class connectionHandler {
    private static final Logger LOGGER = Logger.getLogger(connectionHandler.class.getName());
    private ServerSocketChannel serverSocket;
    private ExecutorService readThreadPool;
    private ForkJoinPool processThreadPool;

    public connectionHandler() throws IOException {
        String num = System.getenv("LAB_PORT");
        Integer port = null;
        try {
            port = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            System.out.println("Неверный аргумент (порт)");
            System.exit(0);
        }
        if ((0 > port) || (port > 65535)) {
            System.out.println("Неверный аргумент (порт)");
            System.exit(0);
        }
        serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        LOGGER.info("Инициализация сервера успешна");

        readThreadPool = Executors.newCachedThreadPool();

        processThreadPool = new ForkJoinPool();
    }

    public void startServer() {
        Thread consoleInputThread = new Thread(this::checkConsoleInput);
        consoleInputThread.start();
        while (true) {
            try {
                SocketChannel clientSocket = serverSocket.accept();
                if (clientSocket != null) {
                    LOGGER.info("Новое соединение установлено с " + clientSocket.getRemoteAddress());
                    readThreadPool.submit(() -> {
                        try {
                            handleClientConnection(clientSocket);
                        } catch (IOException e) {
                            LOGGER.warning("Ошибка при обработке соединения с клиентом: " + e.getMessage());
                        }
                    });
                }
            } catch (IOException e) {
                LOGGER.warning("Ошибка при установлении соединения: " + e.getMessage());
            }
        }
    }

    private void handleClientConnection(SocketChannel clientSocket) throws IOException {
        while (true) {
            try {
                byte[] data = receiveData(clientSocket);
                    processThreadPool.submit(() -> {
                    Request request = SerializationUtils.deserialize(data);
                    Status status = request.execute();
                    byte[] response = SerializationUtils.serialize(status);
                    sendData(clientSocket, response);
                });
            } catch (IOException e) {
                LOGGER.warning("Ошибка при получении данных от " + clientSocket.getRemoteAddress() + ": " + e.getMessage());
                clientSocket.close();
                break;
            }
        }
    }

    private void sendData(SocketChannel client, byte[] data) {
        new Thread(() -> {
            try {
                LOGGER.info("Начата отправка данных на " + client.getRemoteAddress());
                ByteBuffer lengthBuffer = ByteBuffer.allocate(Integer.BYTES);
                lengthBuffer.putInt(data.length);
                lengthBuffer.flip();
                while (lengthBuffer.hasRemaining()) {
                    client.write(lengthBuffer);
                }

                ByteBuffer dataBuffer = ByteBuffer.wrap(data);
                while (dataBuffer.hasRemaining()) {
                    client.write(dataBuffer);
                }
                LOGGER.info("Отправка данных на " + client.getRemoteAddress() + " успешно завершена");
            } catch (IOException e) {
                LOGGER.warning("Ошибка при отправке данных на " + client + ": " + e.getMessage());
            }
        }).start();
    }


    private byte[] receiveData(SocketChannel clientSocket) throws IOException {
        LOGGER.info("Начато получение данных с " + clientSocket.getRemoteAddress());
        ByteBuffer lengthBuffer = ByteBuffer.allocate(Integer.BYTES);
        while (lengthBuffer.hasRemaining()) {
            clientSocket.read(lengthBuffer);
        }
        lengthBuffer.flip();
        int length = lengthBuffer.getInt();
        ByteBuffer dataBuffer = ByteBuffer.allocate(length);
        while (dataBuffer.hasRemaining()) {
            clientSocket.read(dataBuffer);
        }
        LOGGER.info("Получение данных с " + clientSocket.getRemoteAddress() + " успешно завершено");
        return dataBuffer.array();
    }

    private void checkConsoleInput() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                if (reader.ready()) {
                    String arg = reader.readLine();
                    Command command = null;
                    try {
                        command = commandsServerHandler.getCommand(arg);
                        command.execute(null);
                    } catch (IncorrectInputException e) {
                        terminalHandler.println("!!!Такой команды не существует");
                    }
                }
                Thread.sleep(100);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
