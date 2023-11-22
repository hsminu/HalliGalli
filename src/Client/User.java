package Client;
import Common.Player;
import Common.Room;
import Server.GameRoom;
import Server.HGServerMain;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class User{
    private static final long serialVersionUID = 1L;

    public Player player;

    private HGClientMain MainFrame;

    private String SERVER_IP;
    private int SERVER_PORT;

    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public User(String name, String SERVER_IP, int SERVER_PORT, HGClientMain parent) {
        player = new Player(name);
        this.SERVER_IP = SERVER_IP;
        this.SERVER_PORT = SERVER_PORT;
        this.MainFrame = parent;
    }

    public void Connect() throws IOException{
        try {
            socket = new Socket(SERVER_IP,SERVER_PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            System.out.println("Connected to server. Type 'exit' to quit.");
            objectOutputStream.writeObject(player);

        } catch (IOException e) {
            throw new IOException("Failed to connect to server", e);
        }
    }

    public void CreateRoom(Room room) {
        try {
            objectOutputStream.writeObject("CreateRoom");
            objectOutputStream.writeObject(room);
            objectOutputStream.writeObject(player);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void JoinRoom(Room room) throws IOException{
        try{
            objectOutputStream.writeObject("JoinRoom");
            objectOutputStream.writeObject(room);
            objectOutputStream.writeObject(player);
        } catch (IOException e){
            throw new IOException("Failed to Join room: "+ room.getRoomName());
        }
    }

    public List<Room> getRooms() {
        try {
            objectOutputStream.writeObject("GetRooms");
            System.out.println("GetRooms");
            return (List<Room>) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Player> getRoomPlayers(Room room){
        try{
            objectOutputStream.writeObject("GetRoomPlayers");
            objectOutputStream.writeObject(room);
            List<Player> Lp = new ArrayList<>();
            while(true){
                Object receivedObject = objectInputStream.readObject();
                if(receivedObject == null) break;
                if(receivedObject instanceof String){
                    break;
                }
                if(receivedObject instanceof Player){
                    Lp.add((Player) receivedObject);
                }
            }
            for(Player p: Lp)
                System.out.println(p.getName());
            return Lp;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public void GameStart(){

    }
}

