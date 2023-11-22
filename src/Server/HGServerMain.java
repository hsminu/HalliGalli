package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;

public class HGServerMain {
    private static final int SERVER_PORT = 12345;
    public static List<ClientHandler> clients = new ArrayList<>();
    public static List<GameRoom> gameRooms = new ArrayList<>();
    public HGServerMain(){
        startServer();
    }

    private void startServer(){
        try{
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server is running on port " + SERVER_PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new HGServerMain();
    }

}
