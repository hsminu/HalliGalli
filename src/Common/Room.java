package Common;

import Server.ClientHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    private String roomName;
    private String passWord;
    private int numofPeople;

    public Room(String roomName, String passWord, int numofPeople) {
        this.roomName = roomName;
        this.passWord = passWord;
        this.numofPeople = numofPeople;
    }

    public String getRoomName() {
        return roomName;
    }
    public String getPassWord(){
        return passWord;
    }
    public int getNumofPeople(){
        return numofPeople;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Room otherRoom = (Room) obj;
        return roomName.equals(otherRoom.roomName) && passWord.equals(otherRoom.passWord) && numofPeople == otherRoom.numofPeople;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomName, passWord, numofPeople);
    }

}