package Server;

import Common.HalliGalliCard;
import Common.HalliGalliDeck;
import Common.Player;
import Common.Room;

import java.util.ArrayList;
import java.util.List;

public class GameRoom extends Room {
    private List<Player> players;
    private List<HalliGalliCard> Card_UnderBell;

    private HalliGalliDeck GameDeck;

    public GameRoom(Room room) {
        super(room.getRoomName(), room.getPassWord(), room.getNumofPeople());
        this.players = new ArrayList<>();
    }

    public void AddPlayer(Player player, boolean ishead){
        players.add(player);
        player.isHeadPlayer = ishead;
    }
    
    public List<Player> getPlayers(){
        return players;
    }
}
