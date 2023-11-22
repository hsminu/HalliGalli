package Server;

import Client.HGClientMain;
import Common.Player;
import Common.Room;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable{
    private Socket clientSocket;
    public ObjectInputStream objectInputStream;
    public ObjectOutputStream objectOutputStream;

    private Player player;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            this.objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            this.objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            // 클라이언트로부터 Player 객체 받기
            this.player = (Player)objectInputStream.readObject();
            System.out.println("Received Player: " + this.player.getName());

            // 클라이언트로부터 메시지 받기
            Object receivedObject;
            while ((receivedObject = objectInputStream.readObject()) != null) {

                synchronized (this) {
                    if (receivedObject instanceof String) {
                        String message = (String) receivedObject;


                        if (message.equals("GetRooms")) {
                            SendRooms(this);
                        }

                        if (message.equals("CreateRoom")) {
                            receivedObject = objectInputStream.readObject();
                            GameRoom gameRoom = new GameRoom((Room) receivedObject);
                            receivedObject = objectInputStream.readObject();
                            gameRoom.AddPlayer((Player) receivedObject, true);
                            HGServerMain.gameRooms.add(gameRoom);
                            System.out.println(gameRoom.getPlayers().get(0).getName() + " create room: " + gameRoom.getRoomName());
                        }

                        if (message.equals("JoinRoom")) {
                            receivedObject = objectInputStream.readObject();
                            Room room = (Room) receivedObject;
                            receivedObject = objectInputStream.readObject();
                            for (GameRoom gr : HGServerMain.gameRooms) {
                                if (gr.equals(room) && room.hashCode() == gr.hashCode()) {
                                    gr.AddPlayer((Player) receivedObject, false);
                                    break;
                                }
                            }
                        }

                        if (message.equals("GetRoomPlayers")) {
                            receivedObject = objectInputStream.readObject();
                            Room room = (Room) receivedObject;
                            for (GameRoom gr : HGServerMain.gameRooms) {
                                if (gr.equals(room) || room.hashCode() == gr.hashCode()) {
                                    for(Player p: gr.getPlayers())
                                        objectOutputStream.writeObject(p);
                                    objectOutputStream.writeObject("End");
                                    break;
                                }
                            }
                            objectOutputStream.writeObject(null);
                        }

                        if (message.equals("PlayGame")) {
                            receivedObject = objectInputStream.readObject();
                            Room room = (Room) receivedObject;


                        }

                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 클라이언트가 연결을 종료하면 해당 클라이언트 핸들러 제거
            HGServerMain.clients.remove(this);
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void broadcastMessage(String message) {
        // 서버에서 모든 클라이언트에게 메시지 전송
        for (ClientHandler client : HGServerMain.clients) {
            try {
                client.objectOutputStream.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Player getPlayer(){
        return player;
    }

    private void SendRooms(ClientHandler client){
        try {
            client.objectOutputStream.writeObject(HGServerMain.gameRooms);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}