package handler;

import java.io.*;
import java.net.*;

import exceptions.IncorrectInputException;
import org.apache.commons.lang3.SerializationUtils;
import requests.InfoRequest;
import requests.Request;
import statuses.Status;

public class connectionHandler {
    private ServerSocket serv;
    private OutputStream os;
    private InputStream is;
    private DataOutputStream dos;
    private DataInputStream dis;
    private Socket sock;



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
        sock = new Socket("127.0.0.1", port);
        os = sock.getOutputStream();
        dos = new DataOutputStream(os);
        is = sock.getInputStream();
        dis = new DataInputStream(is);
    }

    public boolean sendData(byte[] data) throws IOException {
        dos.writeInt(data.length);
        dos.flush();
        int curr = 0;
        while (curr < data.length) {
            int length = Math.min(data.length - curr, 1024);
            os.write(data, curr, length);
            os.flush();
            curr += length;
        }
        return true;
    }

    public Status sendCommand(Request request) throws IOException {
        try {
            CommandHandler.getCommand(request.getName());
        } catch (IncorrectInputException ignore) {} //For history

        var data = SerializationUtils.serialize(request);
        sendData(data);
        var returnData = receiveData();
        Status status = SerializationUtils.deserialize(returnData);
        return status;
    }

    public byte[] receiveData() throws IOException {
        int length = dis.readInt();

        byte[] data = new byte[length];
        int curr = 0;
        while (curr < length) {
            int bytesToRead = Math.min(length - curr, 1024);
            int result = is.read(data, curr, bytesToRead);
            if (result == -1) {
                throw new IOException("Ошибка при получении данных с сервера");
            }
            curr += result;
        }
        return data;
    }

}
