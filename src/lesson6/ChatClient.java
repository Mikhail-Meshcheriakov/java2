package lesson6;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 8189;
    private static final String END_MESSAGE = "/end";

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private Scanner scanner;

    public ChatClient() {
        System.out.println("Open connection");
        try {
            openConnection();
        } catch (IOException e) {
            System.out.println("Ошибка соединения с сервером. " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        scanner = new Scanner(System.in);
        new Thread(() -> {
            try {
                while (true) {
                    String strFromServer = in.readUTF();
                    if (strFromServer.equalsIgnoreCase(END_MESSAGE)) {
                        close(socket, in, out);
                        break;
                    }
                    System.out.println("< " + strFromServer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    String strToServer = scanner.nextLine();
                    if (strToServer.equals(END_MESSAGE)) {
                        close(socket, in, out);
                        break;
                    }
                    out.writeUTF(strToServer);
                    System.out.println("> " + strToServer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }

    public void close(Closeable... objects) {
        for (Closeable o : objects) {
            try {
                if (o != null) {
                    o.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ChatClient();
    }
}
