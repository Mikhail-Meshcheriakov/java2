package lesson6;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
    private static final int SERVER_PORT = 8189;
    private static final String END_MESSAGE = "/end";
    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();
                        if (str.equals(END_MESSAGE)) {
                            close(socket, in, out);
                            break;
                        }
                        System.out.println("< " + str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    while (true) {
                        String str = scanner.nextLine();
                        if (str.equals(END_MESSAGE)) {
                            close(socket, in, out);
                            break;
                        }
                        out.writeUTF(str);
                        System.out.println("> " + str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void close(Closeable... objects) {
        for (Closeable o : objects) {
            try {
                o.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
