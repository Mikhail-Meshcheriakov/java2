package lesson7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {
    private final int PORT = 8189;

    private List<ClientHandler> clients;
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            authService = new BaseAuthService();
            authService.start();
            clients = new ArrayList<>();

            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            System.out.println("Ошибка в работе сервера");
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    //Отправка персонального сообщения
    public synchronized void privateMsg(String msg, String nick) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nick)) {
                o.sendMsg(msg);
                break;
            }
        }
    }

    //Отправка списка пользователей
    public synchronized void broadcastUserList() {
        StringBuilder msg = new StringBuilder("/clients ");
        for (ClientHandler o : clients) {
            msg.append(o.getName()).append(" ");
        }
        broadcastMsg(msg.toString());
    }

    public synchronized void unsubscribe(ClientHandler o) {
        clients.remove(o);
    }

    public synchronized void subscribe(ClientHandler o) {
        clients.add(o);
        broadcastUserList();   //При успешной авторизации пользователя, обновляем списки пользователей на всех клиентах
    }
}
